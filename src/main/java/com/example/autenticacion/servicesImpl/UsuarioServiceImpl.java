package com.example.autenticacion.servicesImpl;


import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.entity.UsuarioEntity;
import com.example.autenticacion.mappers.UsuarioMapper;
import com.example.autenticacion.repository.UsuarioRepository;
import com.example.autenticacion.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper; // Aquí se inyecta el mapper de MapStruct

    @Override
    public void guardarUsuario(Usuario user) {
        usuarioRepository.save(usuarioMapper.toEntity(user)); // Usando MapStruct para convertir
    }

    @Override
    public Usuario encriptarPass(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuario;
    }

    @Override
    public List<String> obtenerRolesDeUsuario(String username) {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username).orElseThrow();  // Obtiene el usuario de la DB
        return usuario.getRoles().stream()
                .map(rol -> rol.getNombre())  // Extrae los nombres de los roles (por ejemplo, ROLE_USER, ROLE_ADMIN)
                .collect(Collectors.toList());

    }
}