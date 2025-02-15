package com.example.autenticacion.services;

import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
   void guardarUsuario(Usuario user);
   List<String> obtenerRolesDeUsuario(String username);
   void saveUser(Usuario user);
}
