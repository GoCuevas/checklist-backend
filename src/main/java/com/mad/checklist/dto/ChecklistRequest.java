package com.mad.checklist.dto;

import com.mad.checklist.model.ChecklistItem;
import java.util.List;

public class ChecklistRequest {

    private Long vehiculoId;
    private Integer kilometraje;
    private List<ChecklistItem> items;

    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }

    public Integer getKilometraje() { return kilometraje; }
    public void setKilometraje(Integer kilometraje) { this.kilometraje = kilometraje; }

    public List<ChecklistItem> getItems() { return items; }
    public void setItems(List<ChecklistItem> items) { this.items = items; }
}