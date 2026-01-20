package com.example.ejercicioClase.repository;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {
    //Crear un metodo personalizado de departamento donde los m2 son menores a 55 y su precio
    //sea menor a 6000
    public List<DepartamentoEntity> findByM2LessThanAndPrecioLessThan(Integer m2, Double precio);

    //Crear un metodo personalizado con consultas o querys
    @Query(value = "select * from departamento where m2>=:m2 and precio>=:precio",nativeQuery = true)
    public List<DepartamentoEntity> m2AndPrecioQuery(Integer m2, Double precio);

}
