package com.example.ejercicioClase.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //anotation de lombok para el constructor sin argumentos
@AllArgsConstructor //anotation de lombok para el constructor con todos los argumentos
@Data //getter y setter
public class DepartamentoResponse {
    private Integer id;
    private Integer m2;
}
