package com.erp.Inventory.Management.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDto {
    private Long id;
    private String location;
    private Integer capacity;
}