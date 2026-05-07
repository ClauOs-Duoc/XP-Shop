package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class ComunaDTO {

    private Integer idComuna;
    private String nombreComuna;
    private String nombreRegion;
    private List<String> nombreUsuarios;

}
