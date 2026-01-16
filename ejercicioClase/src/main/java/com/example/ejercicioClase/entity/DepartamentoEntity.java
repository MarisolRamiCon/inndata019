package com.example.ejercicioClase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //anotation de lombok para el constructor sin argumentos
@AllArgsConstructor //anotation de lombok para el constructor con todos los argumentos
@Data //getter y setter

@Entity
@Table(name = "departamento")
public class DepartamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "m2")
    private Integer m2;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "activo")
    private Boolean activo=true;



}
