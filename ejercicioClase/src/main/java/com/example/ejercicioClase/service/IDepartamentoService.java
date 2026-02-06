package com.example.ejercicioClase.service;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import com.example.ejercicioClase.model.reponse.DepartamentoResponse;
import com.example.ejercicioClase.model.request.DepartamentoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IDepartamentoService {
    public List<DepartamentoResponse> readAll();
    //Buscar por id a los departamentos
    public DepartamentoEntity readById(Integer id);
    //Metodos CRUD C-CREATE, R-READ, U - UPDATE, D- DELETE
    public DepartamentoResponse create(DepartamentoRequest departamento);
    public String updateById(Integer id, DepartamentoEntity departamento);
    public String deleteById(Integer id);

    public List<DepartamentoEntity> m2AndPrecio(Integer m2, Double precio);
    public List<DepartamentoEntity> m2AndPrecioQ(Integer m2, Double precio);
}
