package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import com.example.ejercicioClase.model.reponse.DepartamentoResponse;
import com.example.ejercicioClase.model.request.DepartamentoRequest;
import com.example.ejercicioClase.repository.DepartamentoRepository;
import com.example.ejercicioClase.service.IDepartamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartamentoService implements IDepartamentoService {
    @Autowired //Nos dice que vamos a hacer una inyeccion de dependencia
    DepartamentoRepository departamentoRepository;

    @Override
    public List<DepartamentoResponse> readAll() {
        log.info("Iniciando lista de departamentos");
        try{
            return departamentoRepository.findAll().stream().filter(departamento -> departamento.getActivo()).map(dep->{
                    DepartamentoResponse departamentoResponse = new DepartamentoResponse();
                    departamentoResponse.setId(dep.getId());
                    departamentoResponse.setM2(dep.getM2());
            return departamentoResponse;
            }).toList();
        } catch (Exception e) {
            log.error("Error leyendo lista de departamentos", e);
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public DepartamentoEntity readById(Integer id) {
        log.info("Iniciando readById departamento");
        try{
            Optional<DepartamentoEntity> departamentoAbuscar= departamentoRepository.findById(id);
            if(departamentoAbuscar.isPresent()){
                return departamentoAbuscar.get();
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("Error en readById departamento", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public DepartamentoResponse create(DepartamentoRequest departamento) {
        log.info("Iniciando departamento create");
        try{
            DepartamentoEntity departamentoEntity = new DepartamentoEntity();
            departamentoEntity.setM2(departamento.getM2());
            departamentoEntity.setPrecio(departamento.getPrecio());
            departamentoEntity.setActivo(true);

            departamentoEntity = departamentoRepository.save(departamentoEntity);
            DepartamentoResponse departamentoResponse = new DepartamentoResponse();
            departamentoResponse.setId(departamentoEntity.getId());
            departamentoResponse.setM2(departamentoEntity.getM2());

            return departamentoResponse;
        }catch (Exception e){
            log.error("Error creando departamento", e);
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public String updateById(Integer id, DepartamentoEntity departamento) {
        log.info("Iniciando updateById departamento");
        Optional<DepartamentoEntity> departamentoBuscado;
        try{

           departamentoBuscado = departamentoRepository.findById(id);
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

        } catch (Exception e) {
            log.error("Error actualizando departamento", e);
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public String deleteById(Integer id) {
        log.info("Iniciando deleteById departamento");
        try{
            Optional<DepartamentoEntity> departamentoABorrar = departamentoRepository.findById(id);
            if(departamentoABorrar.isPresent()){
                DepartamentoEntity departamento = departamentoABorrar.get();
                departamento.setActivo(false);
                departamentoRepository.save(departamento);
                return "Departamento borrado";
            }else{
                return "No hay tal departamento";
            }
        } catch (Exception e) {
            log.error("Error borrando departamento", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<DepartamentoEntity> m2AndPrecio(Integer m2, Double precio) {
        log.info("Iniciando m2AndPrecio departamento");
        try{
            return departamentoRepository.findByM2LessThanAndPrecioLessThan(m2,precio);

        }catch (Exception e){
            log.error("Error en m2AndPrecio departamento", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<DepartamentoEntity> m2AndPrecioQ(Integer m2, Double precio) {
        log.info("Iniciando m2AndPrecioQ departamento");
        try{
            return departamentoRepository.m2AndPrecioQuery(m2,precio);

        }catch (Exception e){
            log.error("Error en m2AndPrecioQ departamento", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
