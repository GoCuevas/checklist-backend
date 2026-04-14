package com.mad.checklist.service;

import com.mad.checklist.model.*;
import com.mad.checklist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private ChecklistItemRepository itemRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Checklist crearChecklist(Long vehiculoId, Integer kilometraje, List<ChecklistItem> items) {

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        Checklist checklist = new Checklist();
        checklist.setVehiculo(vehiculo);
        checklist.setFecha(LocalDate.now());
        checklist.setHora(LocalTime.now());
        checklist.setKilometraje(kilometraje);

        boolean tieneNoCumple = items.stream()
                .anyMatch(item -> item.getEstado().equalsIgnoreCase("NO_CUMPLE"));

        boolean tieneObservacion = items.stream()
                .anyMatch(item -> item.getEstado().equalsIgnoreCase("OBSERVACION"));

        boolean fallaCritica = items.stream()
                .anyMatch(item -> item.isEsCritico() && item.getEstado().equalsIgnoreCase("NO_CUMPLE"));

        if (fallaCritica) {
            checklist.setEstadoFinal("BLOQUEADO");
            vehiculo.setHabilitado(false);

        } else if (tieneNoCumple) {
            checklist.setEstadoFinal("NO_HABILITADO");
            vehiculo.setHabilitado(false);

        } else if (tieneObservacion) {
            checklist.setEstadoFinal("OBSERVADO");
            vehiculo.setHabilitado(true);

        } else {
            checklist.setEstadoFinal("APROBADO");
            vehiculo.setHabilitado(true);
        }
        vehiculo.setEstado(checklist.getEstadoFinal());

        // guardar cambios en vehículo
        vehiculoRepository.save(vehiculo);

        // 🔥 RELACIÓN BIEN HECHA
        for (ChecklistItem item : items) {
            item.setChecklist(checklist);
        }

        checklist.setItems(items);

        return checklistRepository.save(checklist);
    }
}