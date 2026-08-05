package io.github.macebot.inventory_management_api.auth.controller;

import io.github.macebot.inventory_management_api.security.CustomUserDetailsServiceImpl;
import io.github.macebot.inventory_management_api.security.service.IJwtService;
import io.github.macebot.inventory_management_api.user.dto.CreateUserDto;
import io.github.macebot.inventory_management_api.user.dto.LoginUserDto;
import io.github.macebot.inventory_management_api.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final IJwtService jwtService;
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CreateUserDto dto){
        userService.createUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto loginDto) {

        /*
         * Solicita al AuthenticationManager que autentique las credenciales.
         * El AuthenticationManager delega la autenticación al AuthenticationProvider
         * configurado (DaoAuthenticationProvider), que:
         *  - carga el usuario mediante CustomUserDetailsService,
         *  - compara la contraseña usando PasswordEncoder,
         *  - y devuelve un Authentication autenticado.
         * Si las credenciales son incorrectas, se lanza una excepción.
         */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.username(),
                        loginDto.password()
                )
        );

        /*
         * Obtiene el UserDetails autenticado.
         * Ya no es necesario volver a consultar la base de datos.
         */
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        /*
         * Genera un JWT con la información necesaria del usuario
         * (por ejemplo username, roles y fecha de expiración).
         */
        String jwt = jwtService.generateToken(userDetails);

        /*
         * Devuelve el token al cliente para que lo envíe en el header
         * Authorization: Bearer <token> en las siguientes peticiones.
         */
        return ResponseEntity.ok(jwt);
    }


}
