/* 
package proyecto.lp.iii.api.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.repository.UsuariosRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends GenericFilter{
    @Autowired
    private  UsuariosRepository usuariosRepository;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            
            Optional<Usuarios> match = usuariosRepository.findAll().stream()
                .filter(r -> token.equals(r.getAccess_token()))
                .findFirst();

            if (match.isPresent()) {
                String clienteId = match.get().getCliente_id();
                UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(clienteId, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req, res);        
    }

}
    */