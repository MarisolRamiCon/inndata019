package com.example.ejercicioClase.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //anotation de lombok para el constructor sin argumentos
@AllArgsConstructor //anotation de lombok para el constructor con todos los argumentos
@Data //getter y setter
public class DepartamentoRequest {

    private Integer id;
    private Integer m2;
    private Double precio;
}
