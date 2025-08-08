package com.example.energy.service;

import com.example.energy.model.EnergyUsage;
import com.example.energy.repository.EnergyUsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnergyUsageService {
    private final EnergyUsageRepository repo;

    public EnergyUsageService(EnergyUsageRepository repo) {
        this.repo = repo;
    }

    public EnergyUsage save(EnergyUsage usage) {
        return repo.save(usage);
    }

    public List<EnergyUsage> listAll() {
        return repo.findAll();
    }

    public List<EnergyUsage> listBetween(LocalDate start, LocalDate end) {
        return repo.findByDateBetweenOrderByDateDesc(start, end);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
