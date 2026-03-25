package com.mad.checklist.controller;

import com.mad.checklist.model.Checklist;
import com.mad.checklist.model.ChecklistItem;
import com.mad.checklist.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mad.checklist.dto.ChecklistRequest;

import java.util.List;

@RestController
@RequestMapping("/checklists")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @PostMapping
    public Checklist crearChecklist(@RequestBody ChecklistRequest request) {

        return checklistService.crearChecklist(
                request.getVehiculoId(),
                request.getKilometraje(),
                request.getItems()
        );
    }
}