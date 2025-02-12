package com.example.autenticacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="rol")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    private Date fechaCreacion;

    private Date FechaModificacion;

    //@ManyToMany(mappedBy = "roles") // La relación inversa
    //private Set<UsuarioEntity> usuarios = new HashSet<>();
}
