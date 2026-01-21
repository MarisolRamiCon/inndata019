package com.example.ejercicioClase.service.impl;

import com.example.ejercicioClase.feign.ViajeClient;
import com.example.ejercicioClase.model.Viaje;
import com.example.ejercicioClase.service.IViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ViajeService implements IViajeService {
    @Autowired
    ViajeClient viajeClient;
    @Override
    public List<Viaje> readAll() {
        return viajeClient.readAll();
    }
}
