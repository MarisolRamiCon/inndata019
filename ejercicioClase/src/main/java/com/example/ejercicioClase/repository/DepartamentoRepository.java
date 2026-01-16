package com.example.ejercicioClase.repository;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {
}
