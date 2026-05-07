package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class RegionDTO {

    private Integer idRegion;
    private String nombreRegion;
    private List<String> nombreComunas;

}
