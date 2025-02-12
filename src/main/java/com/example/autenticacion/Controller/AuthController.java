package com.example.autenticacion.Controller;


import com.example.autenticacion.Model.AuthRequest;
import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.Security.JwtUtil;
import com.example.autenticacion.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthRequest>login(@RequestBody Usuario usuario) {
        try {
            //  1. Intentar autenticar usuario con Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
            );
            //  2. Si la autenticación es correcta, generar el JWT
            UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());

            List<String> roles=usuarioService.obtenerRolesDeUsuario(usuario.getUsername());
            String token = jwtUtil.generarToken(userDetails.getUsername(),roles);
            //  3. Devolver el token en la respuesta
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(usuario.getUsername());
            authRequest.setToken(token);
            return ResponseEntity.ok(authRequest);
        } catch (BadCredentialsException e) {
            // Manejar el caso en el que las credenciales sean incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRequest(usuario.getUsername(), "Credenciales Erróneas"));
        }
    }
}
