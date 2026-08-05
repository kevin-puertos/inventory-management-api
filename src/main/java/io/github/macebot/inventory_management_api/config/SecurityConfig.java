package io.github.macebot.inventory_management_api.config;

import io.github.macebot.inventory_management_api.security.ICustomUserDetailsServiceImpl;
import io.github.macebot.inventory_management_api.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final ICustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtFilter jwtFilter;

    //Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http    //Marca que al recibir una peticion use este authentiactionProvider
                //En este caso DAOAuthenticationProvider
                .authenticationProvider(authenticationProvider())

                //Se desactiva porque estamos usando JWT y las solicitudes son sin estados
                //Se envia el token en una cabecera oculta y no lo hace automaticamente el navegador
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests( auth -> auth
                        //Endpoints sin autenticacion
                        .requestMatchers(
                                "/api/login",
                                "/api/register",
                                //Swagger Endpoints
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                                ).permitAll()
                        //Endpoints donde se necesita tener el rol de "ADMIN"
                        //SI no lo tiene -> 403 Forbidden
                        .requestMatchers("/api/users/admin**").hasRole("ADMIN")
                        //Todos los dámas endpoints necesitan autenticación
                        .anyRequest().authenticated()

                  //Entra en nuestro filtro jwt antes que los filtros de spring
                ).addFilterBefore(
                        jwtFilter, UsernamePasswordAuthenticationFilter.class

                //No crea sessiones
                ).sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

    //Si alguien necesita un passwordEnncoder, usa BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    /*
    * El componente AuthenticationManager coordina la autenticación
    * LLama el componente que sepa autenticar  ->AuthenticationProvider -> DaoAuthenticationProvider
    *
    * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws  Exception{
            return config.getAuthenticationManager();
        }

    @Bean
    /*
    * Componente que sabe como autenticar un usuario
    * En este caso DAO(Data Access Object) -> Busca el usuario en la base de datos
    * */
    public AuthenticationProvider authenticationProvider(){
        //Necesita saber  buscar usuarios -> customUserDetailsService
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);

        // Y también necesita saber  comparar contraseñas -> passwordEncoder()
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}



