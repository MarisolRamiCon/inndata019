package com.example.ejercicioClase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Viaje {
    private Integer id;
    private String destino;
    private Double precio;
    private LocalDateTime fechaSalida;
}
