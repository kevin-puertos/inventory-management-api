package com.kevinpuertos.inventory_management_api.security;

import com.kevinpuertos.inventory_management_api.security.service.IJwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final IJwtService jwtService;
    private final ICustomUserDetailsServiceImpl customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {


            String authHeader = request.getHeader("Authorization"); // Obtiene Header "Bearer <token>" de la solicitud HTTP
            if ((authHeader == null) || !authHeader.startsWith("Bearer ")) { // Si no hay token ni empieza con Bearer → continúa (pasarlo como validación vacía para endpoints públicos)
                filterChain.doFilter(request, response); // ejemplo un endpoint /login no necesita una autorizacion
                return; //Continua con los demas filtros
            }

            /*Si sí tiene un Header de autirizacion entonces
            * Obtengo el token de autorizacion que está despues
            * de 7 caracteres "Bearer  defa8fej19s..."
            * Substring se queda con "defa8fej19s... "
            * porque empieza despúes del 7mo carácter "Bearer " -> 7
            * y me quedo con el token
            * */
            String token = authHeader.substring(7);

            /*
            * El token tiene los claims ejemplo: sub,role,exp,email,etc
            * obtengo el sub el cual es actualemente el username
            * y lo guardo en una variable string
            * */

            String username = jwtService.extractAllClaims(token).getSubject();//Obtengo el username del usuario



            /*
            * Si el nombre del usuario es distinto a null o el SecurityContextHolder es null
            * que significa que no hay usuarios autorizados entonces busca el usuarios en la base de datos
            * y lo guarda en una varible UserDetails que necesita Spring para tener toda la informacion necesaria
            * para aplicar la autorizacion
            *
            * */
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = customUserDetailsService.loadUserByUsername(username);

                // Validar que JWT NO haya caducado -> Retornar 401 sí ha expirado
                if (!jwtService.isTokenExpired(token)) {

                    /*Esta es la forma en que spring representa a un usuario autenticado
                    * Contiene
                    * Usuario
                    * Autorities
                    * Credenciales
                    * */
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    // Guardar en el SecurityContextHolder  para que Spring Security pueda validar permisos según roles
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
            filterChain.doFilter(request, response);//Continua con los demas filtros

        }catch (ExpiredJwtException ex){//En  el Scaso que el token se encuentre expirado devuelve una respuesta con el error
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("""
                   {
                     "message":"Token Expired"
                   }
                    """);
        }

    }
}
