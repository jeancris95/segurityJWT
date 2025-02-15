package com.example.autenticacion.Controller;
import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.services.UserDetailService;
import com.example.autenticacion.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro")
@AllArgsConstructor
public class RegistroController {
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public String register(@RequestBody Usuario usuario) {
       usuarioService.saveUser(usuario);
        return "Usuario registrado correctamente";
    }

    @PostMapping("/registere")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String registere(@RequestBody Usuario usuario) {
        return "si tiene acceso";
    }

    @PostMapping("/registero")
    @PreAuthorize("hasRole('USER')")
    public String registero(@RequestBody Usuario usuario) {
        return "si tiene acceso";
    }

    @PostMapping("/registera")
    @PreAuthorize("hasRole('ADMIN')")
    public String registera(@RequestBody Usuario usuario) {
        return "si tiene acceso";
    }



}
