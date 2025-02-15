package com.example.autenticacion.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private Long id;
    private String username;
    private String password;
    private String role;
}
