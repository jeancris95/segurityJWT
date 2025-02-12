package com.example.autenticacion.services;

import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
   void guardarUsuario(Usuario user);
   Usuario encriptarPass(Usuario usuario);
   List<String> obtenerRolesDeUsuario(String username);
}
