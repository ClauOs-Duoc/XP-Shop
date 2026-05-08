package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoriaDTO {

    private Integer idCategoria;
    private String nombreCategoria;
    private List<String> categorias;
    private List<String> productos;

    
}
