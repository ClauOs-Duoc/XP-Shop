package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class MarcaDTO {

    private Integer idMarca;
    private String nombreMarca;
    private List<Integer> marcas;
    
}
