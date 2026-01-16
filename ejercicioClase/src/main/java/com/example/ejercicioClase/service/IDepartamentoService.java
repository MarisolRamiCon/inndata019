package com.example.ejercicioClase.service;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IDepartamentoService {
    public List<DepartamentoEntity> readAll();
    //Buscar por id a los departamentos
    public DepartamentoEntity readById(Integer id);
    //Metodos CRUD C-CREATE, R-READ, U - UPDATE, D- DELETE
    public DepartamentoEntity create(DepartamentoEntity departamento);
    public String updateById(Integer id, DepartamentoEntity departamento);
    public String deleteById(Integer id);
}
