package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.model.PersonaResponse;
import com.example.ejercicioClase.repository.PersonaRepository;
import com.example.ejercicioClase.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonaService implements IPersonaService {
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public List<PersonaResponse> readAll() {
        return personaRepository.findAll().stream().map(
                personaEntity -> {
                    PersonaResponse personaResponse= new PersonaResponse(personaEntity.getNombre(),
                            personaEntity.getEdad(),personaEntity.getIdDepartamento().getId());
                    return personaResponse;
                }
        ).toList();
    }
}
