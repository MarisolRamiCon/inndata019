package com.example.ejercicioClase.controller;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import com.example.ejercicioClase.service.impl.DepartamentoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartamentoController {
    @Autowired
    DepartamentoService departamentoService;
    @GetMapping("/departamentos")
    public List<DepartamentoEntity> readAll(){
        return departamentoService.readAll();
    }
    @GetMapping("/departamentos/{id}")
    public DepartamentoEntity readById(@PathVariable Integer id){
        return departamentoService.readById(id);

    }
    @PostMapping("/departamentos") //Annotation para crear
    public DepartamentoEntity create(@RequestBody DepartamentoEntity departamento){
        return departamentoService.create(departamento);
    }
    //Actualizar
    @PutMapping("/departamentos/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody DepartamentoEntity departamento){
        return departamentoService.updateById(id,departamento);
    }

    //Borrar
    @DeleteMapping("/departamentos/{id}")
    public String deleteById(@PathVariable Integer id){
        return departamentoService.deleteById(id);
    }

    //Metodo personalizado con palabras clave
    @GetMapping("/m2AndPrecio")
    public List<DepartamentoEntity> m2AndPrecio(@PathParam("m2") Integer m2,@PathParam("precio") Double precio){
        return departamentoService.m2AndPrecio(m2,precio);

    }

    //Metodo personalizado con Query
    @GetMapping("/m2AndPrecioQuery")
    public List<DepartamentoEntity> m2AndPrecioQ(@PathParam("m2") Integer m2,@PathParam("precio") Double precio){
        return departamentoService.m2AndPrecioQ(m2,precio);

    }
}
