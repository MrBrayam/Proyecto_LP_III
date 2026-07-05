package proyecto.lp.iii.api.security;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.entity.Tenants;

@ControllerAdvice
public class MultiTenantRequestInterceptor extends RequestBodyAdviceAdapter {

    @Autowired
    private HttpServletRequest servletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            return body;
        }

        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        if (usuario == null || usuario.getId_tenants() == null) {
            return body;
        }

        try {
            Method method = body.getClass().getMethod("setId_tenants", Tenants.class);
            method.invoke(body, usuario.getId_tenants());
        } catch (Exception e) {
            // El objeto no soporta la relación id_tenants o es de tipo diferente
        }

        return body;
    }
}
