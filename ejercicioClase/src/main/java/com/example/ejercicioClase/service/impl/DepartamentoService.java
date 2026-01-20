package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import com.example.ejercicioClase.repository.DepartamentoRepository;
import com.example.ejercicioClase.service.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoService {
    @Autowired //Nos dice que vamos a hacer una inyeccion de dependencia
    DepartamentoRepository departamentoRepository;

    @Override
    public List<DepartamentoEntity> readAll() {
        return departamentoRepository.findAll().stream().filter(departamento -> departamento.getActivo()).toList();
    }

    @Override
    public DepartamentoEntity readById(Integer id) {
        Optional<DepartamentoEntity> departamentoAbuscar= departamentoRepository.findById(id);
        if(departamentoAbuscar.isPresent()){
            return departamentoAbuscar.get();
        }else{
            return null;
        }

    }

    @Override
    public DepartamentoEntity create(DepartamentoEntity departamento) {
        departamentoRepository.save(departamento);
        return departamento;
    }

    @Override
    public String updateById(Integer id, DepartamentoEntity departamento) {
        Optional<DepartamentoEntity> departamentoBuscado = departamentoRepository.findById(id);
        if(departamentoBuscado.isPresent()){
            DepartamentoEntity modificado= new DepartamentoEntity();
            modificado.setId(departamento.getId());
            modificado.setM2(departamento.getM2());
            modificado.setPrecio(departamento.getPrecio());
            departamentoRepository.save(modificado);
            return "Departamento actualizado";
        }else{
            return "Departamento no encontrado";
        }

    }

    @Override
    public String deleteById(Integer id) {
        Optional<DepartamentoEntity> departamentoABorrar = departamentoRepository.findById(id);
        if(departamentoABorrar.isPresent()){
            DepartamentoEntity departamento = departamentoABorrar.get();
            departamento.setActivo(false);
            departamentoRepository.save(departamento);
            return "Departamento borrado";
        }else{
            return "No hay tal departamento";
        }

    }

    @Override
    public List<DepartamentoEntity> m2AndPrecio(Integer m2, Double precio) {
        return departamentoRepository.findByM2LessThanAndPrecioLessThan(m2,precio);
    }

    @Override
    public List<DepartamentoEntity> m2AndPrecioQ(Integer m2, Double precio) {
        return departamentoRepository.m2AndPrecioQuery(m2,precio);
    }

}
