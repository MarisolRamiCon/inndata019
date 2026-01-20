package com.example.ejercicioClase.repository;

import com.example.ejercicioClase.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
}
