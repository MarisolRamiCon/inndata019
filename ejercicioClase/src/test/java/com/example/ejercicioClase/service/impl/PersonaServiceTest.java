package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import com.example.ejercicioClase.entity.PersonaEntity;
import com.example.ejercicioClase.model.PersonaResponse;
import com.example.ejercicioClase.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    PersonaRepository personaRepository;

    @InjectMocks
    PersonaService personaService;

    @Test
    void readAll_mapsEntitiesToResponses() {
        DepartamentoEntity dep = new DepartamentoEntity();
        dep.setId(11);
        PersonaEntity p = new PersonaEntity();
        p.setId(1);
        p.setNombre("Juan");
        p.setEdad(30);
        p.setIdDepartamento(dep);

        when(personaRepository.findAll()).thenReturn(List.of(p));

        var result = personaService.readAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        PersonaResponse r = result.get(0);
        assertEquals("Juan", r.getNombre());
        assertEquals(30, r.getEdad());
        assertEquals(11, r.getIdDepartamento());
    }

    @Test
    void readAll_emptyList_returnsEmpty() {
        when(personaRepository.findAll()).thenReturn(List.of());
        var result = personaService.readAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void readAll_repoThrows_rethrows() {
        when(personaRepository.findAll()).thenThrow(new RuntimeException("fail-pe"));
        assertThrows(RuntimeException.class, () -> personaService.readAll());
    }
}

