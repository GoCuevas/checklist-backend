package com.mad.checklist.controller;

import com.mad.checklist.model.Vehiculo;
import com.mad.checklist.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // Crear vehículo
    @PostMapping
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    // Listar vehículos
    @GetMapping
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }

    @GetMapping("/{id}/estado")
    public Map<String, Object> obtenerEstadoVehiculo(@PathVariable Long id) {

        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        Map<String, Object> response = new HashMap<>();
        response.put("habilitado", vehiculo.isHabilitado());

        if (vehiculo.isHabilitado()) {
            response.put("mensaje", "Vehículo habilitado para operar");
        } else {
            response.put("mensaje", "Vehículo bloqueado por falla crítica");
        }

        return response;
    }
}