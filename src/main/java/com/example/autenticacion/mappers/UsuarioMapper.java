package com.example.autenticacion.mappers;

import com.example.autenticacion.Model.Usuario;
import com.example.autenticacion.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // Mapea de UsuarioEntity a UsuarioDTO
    Usuario toDTO(UsuarioEntity usuarioEntity);

    // Mapea de UsuarioDTO a UsuarioEntity
    UsuarioEntity toEntity(Usuario usuarioDTO);
}
