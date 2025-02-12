package com.example.autenticacion.services;

import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.entity.UsuarioEntity;
import com.example.autenticacion.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//Spring Security necesita un UserDetailsService para obtener los datos del usuario y autenticarlo.
@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    //con este metodo recuperamos el usuario a través del usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre().toUpperCase()))
                .collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }
}
