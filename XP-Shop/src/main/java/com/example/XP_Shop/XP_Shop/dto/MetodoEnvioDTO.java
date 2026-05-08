package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class MetodoEnvioDTO {

    private Integer idMetodoEnvio;
    private String nombreMetodoEnvio;
    private List<Integer> boletas;

}
