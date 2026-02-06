package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.feign.ViajeClient;
import com.example.ejercicioClase.model.Viaje;
import com.example.ejercicioClase.service.impl.ViajeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViajeServiceTest {

    @Mock
    ViajeClient viajeClient;

    @InjectMocks
    ViajeService viajeService;

    @Test
    void readAll_delegatesToClient() {
        Viaje v = new Viaje();
        v.setId(1);
        v.setDestino("Lima");
        v.setPrecio(10.0);
        when(viajeClient.readAll()).thenReturn(List.of(v));

        var result = viajeService.readAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Lima", result.get(0).getDestino());
    }

    @Test
    void readAll_clientThrows_rethrows() {
        when(viajeClient.readAll()).thenThrow(new RuntimeException("feign fail"));
        assertThrows(RuntimeException.class, () -> viajeService.readAll());
    }
}
