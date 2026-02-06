package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.entity.DepartamentoEntity;
import com.example.ejercicioClase.model.reponse.DepartamentoResponse;
import com.example.ejercicioClase.model.request.DepartamentoRequest;
import com.example.ejercicioClase.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {

    @Mock
    DepartamentoRepository departamentoRepository;

    @InjectMocks
    DepartamentoService departamentoService;

    @Test
    void readAll_filtersInactiveAndMaps() {
        DepartamentoEntity active = new DepartamentoEntity();
        active.setId(1);
        active.setM2(50);
        active.setActivo(true);

        DepartamentoEntity inactive = new DepartamentoEntity();
        inactive.setId(2);
        inactive.setM2(30);
        inactive.setActivo(false);

        when(departamentoRepository.findAll()).thenReturn(List.of(active, inactive));

        var result = departamentoService.readAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        DepartamentoResponse dto = result.get(0);
        assertEquals(1, dto.getId());
        assertEquals(50, dto.getM2());
        verify(departamentoRepository).findAll();
    }

    @Test
    void readAll_repositoryThrows_rethrowsRuntime() {
        when(departamentoRepository.findAll()).thenThrow(new RuntimeException("boom"));
        assertThrows(RuntimeException.class, () -> departamentoService.readAll());
    }

    @Test
    void readById_present() {
        DepartamentoEntity entity = new DepartamentoEntity();
        entity.setId(10);
        when(departamentoRepository.findById(10)).thenReturn(Optional.of(entity));

        var result = departamentoService.readById(10);

        assertNotNull(result);
        assertEquals(10, result.getId());
    }

    @Test
    void readById_absent() {
        when(departamentoRepository.findById(99)).thenReturn(Optional.empty());

        var result = departamentoService.readById(99);

        assertNull(result);
    }

    @Test
    void readById_repositoryThrows_rethrows() {
        when(departamentoRepository.findById(5)).thenThrow(new RuntimeException("boom2"));
        assertThrows(RuntimeException.class, () -> departamentoService.readById(5));
    }

    @Test
    void create_savesAndReturnsResponse() {
        DepartamentoRequest req = new DepartamentoRequest();
        req.setM2(75);
        req.setPrecio(120000.0);

        DepartamentoEntity saved = new DepartamentoEntity();
        saved.setId(5);
        saved.setM2(75);
        saved.setPrecio(120000.0);
        saved.setActivo(true);

        when(departamentoRepository.save(any(DepartamentoEntity.class))).thenReturn(saved);

        var response = departamentoService.create(req);

        assertNotNull(response);
        assertEquals(5, response.getId());
        assertEquals(75, response.getM2());
        verify(departamentoRepository).save(any(DepartamentoEntity.class));
    }

    @Test
    void create_repositoryThrows_rethrows() {
        DepartamentoRequest req = new DepartamentoRequest();
        req.setM2(1);
        req.setPrecio(1.0);
        when(departamentoRepository.save(any())).thenThrow(new RuntimeException("save-fail"));
        assertThrows(RuntimeException.class, () -> departamentoService.create(req));
    }

    @Test
    void updateById_present_updates() {
        DepartamentoEntity existing = new DepartamentoEntity();
        existing.setId(7);
        existing.setM2(40);
        existing.setPrecio(80000.0);

        when(departamentoRepository.findById(7)).thenReturn(Optional.of(existing));

        DepartamentoEntity updateParam = new DepartamentoEntity();
        updateParam.setId(7);
        updateParam.setM2(45);
        updateParam.setPrecio(85000.0);

        var result = departamentoService.updateById(7, updateParam);

        assertEquals("Departamento actualizado", result);
        ArgumentCaptor<DepartamentoEntity> captor = ArgumentCaptor.forClass(DepartamentoEntity.class);
        verify(departamentoRepository).save(captor.capture());
        DepartamentoEntity saved = captor.getValue();
        assertEquals(7, saved.getId());
        assertEquals(45, saved.getM2());
        assertEquals(85000.0, saved.getPrecio());
    }

    @Test
    void updateById_absent_returnsNotFound() {
        when(departamentoRepository.findById(20)).thenReturn(Optional.empty());

        DepartamentoEntity param = new DepartamentoEntity();
        param.setId(20);
        param.setM2(10);
        param.setPrecio(10000.0);

        var result = departamentoService.updateById(20, param);

        assertEquals("Departamento no encontrado", result);
        verify(departamentoRepository, never()).save(any());
    }

    @Test
    void updateById_repoThrows_rethrows() {
        when(departamentoRepository.findById(2)).thenThrow(new RuntimeException("fail"));
        assertThrows(RuntimeException.class, () -> departamentoService.updateById(2, new DepartamentoEntity()));
    }

    @Test
    void deleteById_present_setsActivoFalse() {
        DepartamentoEntity existing = new DepartamentoEntity();
        existing.setId(3);
        existing.setActivo(true);

        when(departamentoRepository.findById(3)).thenReturn(Optional.of(existing));

        var result = departamentoService.deleteById(3);

        assertEquals("Departamento borrado", result);
        ArgumentCaptor<DepartamentoEntity> captor = ArgumentCaptor.forClass(DepartamentoEntity.class);
        verify(departamentoRepository).save(captor.capture());
        assertFalse(captor.getValue().getActivo());
    }

    @Test
    void deleteById_absent_returnsNoFound() {
        when(departamentoRepository.findById(999)).thenReturn(Optional.empty());

        var result = departamentoService.deleteById(999);

        assertEquals("No hay tal departamento", result);
        verify(departamentoRepository, never()).save(any());
    }

    @Test
    void deleteById_repoThrows_rethrows() {
        when(departamentoRepository.findById(4)).thenThrow(new RuntimeException("boom-delete"));
        assertThrows(RuntimeException.class, () -> departamentoService.deleteById(4));
    }

    @Test
    void m2AndPrecio_delegatesToRepository() {
        DepartamentoEntity a = new DepartamentoEntity();
        DepartamentoEntity b = new DepartamentoEntity();
        when(departamentoRepository.findByM2LessThanAndPrecioLessThan(50, 200000.0))
                .thenReturn(List.of(a, b));

        var result = departamentoService.m2AndPrecio(50, 200000.0);

        assertEquals(2, result.size());
        verify(departamentoRepository).findByM2LessThanAndPrecioLessThan(50, 200000.0);
    }

    @Test
    void m2AndPrecio_repoThrows_rethrows() {
        when(departamentoRepository.findByM2LessThanAndPrecioLessThan(1, 1.0))
                .thenThrow(new RuntimeException("m2fail"));
        assertThrows(RuntimeException.class, () -> departamentoService.m2AndPrecio(1,1.0));
    }

    @Test
    void m2AndPrecioQ_delegatesToRepository() {
        DepartamentoEntity a = new DepartamentoEntity();
        when(departamentoRepository.m2AndPrecioQuery(30, 100000.0)).thenReturn(List.of(a));

        var result = departamentoService.m2AndPrecioQ(30, 100000.0);

        assertEquals(1, result.size());
        verify(departamentoRepository).m2AndPrecioQuery(30, 100000.0);
    }

    @Test
    void m2AndPrecioQ_repoThrows_rethrows() {
        when(departamentoRepository.m2AndPrecioQuery(2,2.0)).thenThrow(new RuntimeException("qfail"));
        assertThrows(RuntimeException.class, () -> departamentoService.m2AndPrecioQ(2,2.0));
    }
}

