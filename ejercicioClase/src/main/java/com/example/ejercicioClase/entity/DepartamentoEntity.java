package com.example.ejercicioClase.entity;

import jakarta.persistence.*;

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

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Integer id, Integer m2, Double precio) {
        this.id = id;
        this.m2 = m2;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getM2() {
        return m2;
    }

    public void setM2(Integer m2) {
        this.m2 = m2;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


}
