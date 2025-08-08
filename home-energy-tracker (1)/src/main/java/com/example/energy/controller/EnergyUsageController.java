package com.example.energy.controller;

import com.example.energy.model.EnergyUsage;
import com.example.energy.service.EnergyUsageService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/usages")
@CrossOrigin(origins = "*")
public class EnergyUsageController {
    private final EnergyUsageService service;

    public EnergyUsageController(EnergyUsageService service) {
        this.service = service;
    }

    @GetMapping
    public List<EnergyUsage> all() {
        return service.listAll();
    }

    @GetMapping("/range")
    public List<EnergyUsage> range(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.listBetween(start, end);
    }

    @PostMapping
    public ResponseEntity<EnergyUsage> create(@Valid @RequestBody EnergyUsage usage) {
        EnergyUsage saved = service.save(usage);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
