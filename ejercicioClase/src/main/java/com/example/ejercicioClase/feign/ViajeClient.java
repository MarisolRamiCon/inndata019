package com.example.ejercicioClase.feign;

import com.example.ejercicioClase.model.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "viajes", url = "https://6721642e98bbb4d93ca84a26.mockapi.io/api/v4")
public interface ViajeClient {
    @GetMapping("/viajes")
    public List<Viaje> readAll();
}
