package com.example.energy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "energy_usage")
public class EnergyUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @NotBlank
    private String appliance;

    @Min(0)
    private double kwh;

    public EnergyUsage() {}

    public EnergyUsage(LocalDate date, String appliance, double kwh) {
        this.date = date;
        this.appliance = appliance;
        this.kwh = kwh;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getAppliance() { return appliance; }
    public void setAppliance(String appliance) { this.appliance = appliance; }
    public double getKwh() { return kwh; }
    public void setKwh(double kwh) { this.kwh = kwh; }
}
