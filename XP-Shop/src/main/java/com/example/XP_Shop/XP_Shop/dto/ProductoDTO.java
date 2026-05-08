package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer idProducto;
    private String nombreProducto;
    private Double precio;
    private String descripcionProducto;
    private Integer stock;
    private List<String> productos;
    private String marca;
    private String categoria;
    private List<String> imagenes;
    
}
