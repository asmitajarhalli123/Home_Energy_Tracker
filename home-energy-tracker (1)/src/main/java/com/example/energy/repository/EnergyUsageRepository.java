package com.example.energy.repository;

import com.example.energy.model.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, Long> {
    List<EnergyUsage> findByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end);
}
