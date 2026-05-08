package com.example.XP_Shop.XP_Shop.dto;

import lombok.Data;

@Data
public class DetalleBoletaDTO {

    private Integer idDetalleBoleta;
    private Integer cantidad;
    private Double subtotal;
    private Integer boleta;
    private String producto;
    
}
