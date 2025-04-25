package com.erp.Inventory.Management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouse")
@Data
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String location;
    private Integer capacity;

    public Warehouse() {}

    public Warehouse(Integer id, String location, Integer capacity) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
