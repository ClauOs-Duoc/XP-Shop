package com.example.XP_Shop.XP_Shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class MetodoPagoDTO {

    private Integer idMetodoPago;
    private String nombreMetodoPago;
    private List<Integer> boletas;

}
