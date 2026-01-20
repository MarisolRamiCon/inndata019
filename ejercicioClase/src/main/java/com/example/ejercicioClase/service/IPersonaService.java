package com.example.ejercicioClase.service;

import com.example.ejercicioClase.entity.PersonaEntity;
import com.example.ejercicioClase.model.PersonaResponse;

import java.util.List;

public interface IPersonaService {
    public List<PersonaResponse> readAll();
}
