package com.erp.Inventory.Management.dto;

import lombok.*;

@Data

@Builder
public class WarehouseDto {
    private Long id;
    private String location;

    public WarehouseDto() {

    }

    public WarehouseDto(Long id, String location, Integer capacity) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
    }

    private Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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