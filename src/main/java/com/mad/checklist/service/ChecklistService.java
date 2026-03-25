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

        boolean fallaCritica = items.stream()
                .anyMatch(item -> item.isEsCritico() && item.getEstado().equalsIgnoreCase("FAIL"));

        checklist.setEstadoFinal(fallaCritica ? "RECHAZADO" : "APROBADO");

        // 🔥 RELACIÓN BIEN HECHA
        for (ChecklistItem item : items) {
            item.setChecklist(checklist);
        }

        checklist.setItems(items);

        return checklistRepository.save(checklist);
    }
}