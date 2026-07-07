package proyecto.lp.iii.api.security;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.entity.Tenants;

@ControllerAdvice
public class MultiTenantResponseInterceptor implements ResponseBodyAdvice<Object> {

    @Autowired
    private HttpServletRequest servletRequest;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            return body;
        }

        Integer userTenantId = (Integer) session.getAttribute("userTenantId");
        if (userTenantId == null) {
            return body;
        }

        if (body instanceof List) {
            List<?> list = (List<?>) body;
            List<Object> filteredList = new ArrayList<>();
            for (Object item : list) {
                if (item == null) continue;
                
                if (item instanceof Tenants) {
                    if (((Tenants) item).getId_tenants().equals(userTenantId)) {
                        filteredList.add(item);
                    }
                } else {
                    Integer itemTenantId = getTenantIdFromObject(item);
                    if (itemTenantId == null || itemTenantId.equals(userTenantId)) {
                        filteredList.add(item);
                    }
                }
            }
            return filteredList;
        } else if (body != null) {
            if (body instanceof Tenants) {
                if (!((Tenants) body).getId_tenants().equals(userTenantId)) {
                    return null;
                }
            } else {
                Integer itemTenantId = getTenantIdFromObject(body);
                if (itemTenantId != null && !itemTenantId.equals(userTenantId)) {
                    return null;
                }
            }
        }

        return body;
    }

    private Integer getTenantIdFromObject(Object obj) {
        try {
            Method method = obj.getClass().getMethod("getId_tenants");
            Object tenantObj = method.invoke(obj);
            if (tenantObj instanceof Tenants) {
                return ((Tenants) tenantObj).getId_tenants();
            } else if (tenantObj instanceof Integer) {
                return (Integer) tenantObj;
            }
        } catch (Exception e) {
            // El objeto no contiene la relación o el getter correspondiente
        }
        return null;
    }
}
