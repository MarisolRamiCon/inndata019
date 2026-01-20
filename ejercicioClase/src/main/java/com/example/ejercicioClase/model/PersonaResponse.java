package com.example.ejercicioClase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PersonaResponse {
    private String nombre;
    private Integer edad;
    private Integer idDepartamento;
}
