package proyecto.lp.iii.api.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import proyecto.lp.iii.api.entity.Auditoria;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.service.IAuditoriaService;
import proyecto.lp.iii.api.service.ITenantsService;
import proyecto.lp.iii.api.service.IUsuariosService;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    @Lazy
    private IAuditoriaService auditoriaService;

    @Autowired
    @Lazy
    private ITenantsService tenantService;

    @Autowired
    @Lazy
    private IUsuariosService usuariosService;

    // ─── Interceptar guardar (INSERT) ──────────────────────────────────────────
    @Around("execution(* proyecto.lp.iii.api.service.jpa.*.guardar(..))")
    public Object aroundGuardar(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        try {
            Object entity = pjp.getArgs().length > 0 ? pjp.getArgs()[0] : null;
            String tableName = resolveTableName(pjp);
            Integer idRegistro = resolveId(entity);
            registrarAuditoria("INSERT", tableName, idRegistro, null, entity);
        } catch (Exception ignored) { }
        return result;
    }

    // ─── Interceptar modificar (UPDATE) ────────────────────────────────────────
    @Around("execution(* proyecto.lp.iii.api.service.jpa.*.modificar(..))")
    public Object aroundModificar(ProceedingJoinPoint pjp) throws Throwable {
        Object entidadNueva = pjp.getArgs().length > 0 ? pjp.getArgs()[0] : null;
        String tableName = resolveTableName(pjp);
        String datosAnteriores = serializarEntidad(entidadNueva);

        Object result = pjp.proceed();

        try {
            Integer idRegistro = resolveId(entidadNueva);
            registrarAuditoria("UPDATE", tableName, idRegistro, datosAnteriores, entidadNueva);
        } catch (Exception ignored) { }
        return result;
    }

    // ─── Interceptar eliminar (DELETE) ─────────────────────────────────────────
    @Around("execution(* proyecto.lp.iii.api.service.jpa.*.eliminar(..))")
    public Object aroundEliminar(ProceedingJoinPoint pjp) throws Throwable {
        Object idArg = pjp.getArgs().length > 0 ? pjp.getArgs()[0] : null;
        String tableName = resolveTableName(pjp);
        Integer idRegistro = (idArg instanceof Integer) ? (Integer) idArg : null;

        Object result = pjp.proceed();

        try {
            registrarAuditoria("DELETE", tableName, idRegistro, null, null);
        } catch (Exception ignored) { }
        return result;
    }

    // ─── Helpers ────────────────────────────────────────────────────────────────

    private void registrarAuditoria(String accion, String tabla, Integer idRegistro,
            String datosAnteriores, Object entidadNueva) {
        try {
            // Evitar auto-auditar la tabla de auditoría misma
            if ("auditorias".equals(tabla) || "auditoria".equals(tabla)) return;

            Auditoria audit = new Auditoria();
            audit.setAccion(accion);
            audit.setTabla_afectada(tabla);
            audit.setId_registro(idRegistro);
            audit.setDatos_anteriores(datosAnteriores);
            audit.setDatos_nuevos(serializarEntidad(entidadNueva));
            audit.setFecha_hora(LocalDateTime.now());

            // Obtener datos de la sesión HTTP activa
            ServletRequestAttributes attrs =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest req = attrs.getRequest();
                HttpSession session = req.getSession(false);
                audit.setIp_address(obtenerIp(req));

                if (session != null) {
                    Integer tenantId = (Integer) session.getAttribute("userTenantId");
                    if (tenantId == null) tenantId = (Integer) session.getAttribute("tenantId");
                    if (tenantId != null) {
                        final Integer tid = tenantId;
                        tenantService.buscarId(tid).ifPresent(t -> {
                            Tenants proxy = new Tenants();
                            proxy.setId_tenants(t.getId_tenants());
                            audit.setId_tenants(proxy);
                        });
                    }

                    Integer usuarioId = (Integer) session.getAttribute("userId");
                    if (usuarioId != null) {
                        usuariosService.buscarId(usuarioId).ifPresent(u -> {
                            Usuarios proxy = new Usuarios();
                            proxy.setId_usuarios(u.getId_usuarios());
                            audit.setId_usuarios(proxy);
                        });
                    }
                }
            }

            // Solo guardar si tiene tenant (obligatorio en la tabla)
            if (audit.getId_tenants() != null) {
                auditoriaService.guardar(audit);
            }
        } catch (Exception e) {
            // La auditoría nunca debe romper el flujo principal
            System.err.println("[AuditAspect] Error al registrar auditoría en '" + tabla + "': " + e.getMessage());
        }
    }

    /** Serializa una entidad a JSON-like string usando reflection sobre los getters. */
    private String serializarEntidad(Object entity) {
        if (entity == null) return null;
        try {
            Map<String, String> map = new LinkedHashMap<>();
            for (Method m : entity.getClass().getMethods()) {
                String name = m.getName();
                if (!name.startsWith("get") || name.equals("getClass") || m.getParameterCount() != 0) continue;
                String key = Character.toLowerCase(name.charAt(3)) + name.substring(4);
                try {
                    Object val = m.invoke(entity);
                    if (val == null) {
                        map.put(key, "null");
                    } else if (val instanceof Iterable || val.getClass().isArray()) {
                        map.put(key, "\"[collection]\"");
                    } else if (val.getClass().getName().startsWith("proyecto.lp.iii")) {
                        // Relación JPA: solo guardar el ID
                        Integer relId = resolveId(val);
                        map.put(key, relId != null ? relId.toString() : "null");
                    } else {
                        String strVal = val.toString()
                                .replace("\\", "\\\\")
                                .replace("\"", "\\\"");
                        map.put(key, "\"" + strVal + "\"");
                    }
                } catch (Exception ignored) {
                    map.put(key, "\"?\"");
                }
            }
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (!first) sb.append(",");
                sb.append("\"").append(e.getKey()).append("\":").append(e.getValue());
                first = false;
            }
            sb.append("}");
            return sb.toString();
        } catch (Exception e) {
            return "{\"error\":\"no serializable\"}";
        }
    }

    private String resolveTableName(ProceedingJoinPoint pjp) {
        String className = pjp.getTarget().getClass().getSimpleName();
        if (className.contains("$$")) {
            className = className.substring(0, className.indexOf("$$"));
        }
        className = className.replace("Service", "");
        return camelToSnake(className) + "s";
    }

    private String camelToSnake(String s) {
        return s.replaceAll("([A-Z])", "_$1").toLowerCase().replaceFirst("^_", "");
    }

    private Integer resolveId(Object entity) {
        if (entity == null) return null;
        try {
            for (Method m : entity.getClass().getMethods()) {
                String name = m.getName();
                if ((name.startsWith("getId_") || name.equals("getId"))
                        && m.getParameterCount() == 0
                        && (m.getReturnType() == Integer.class || m.getReturnType() == int.class)) {
                    return (Integer) m.invoke(entity);
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    private String obtenerIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) ip = req.getRemoteAddr();
        return ip;
    }
}
