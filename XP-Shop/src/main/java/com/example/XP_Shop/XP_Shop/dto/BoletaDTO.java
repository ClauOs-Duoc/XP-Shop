package com.example.XP_Shop.XP_Shop.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoletaDTO {

    private Integer idBoleta;
    private LocalDate fechaCompra;
    private Double totalCompra;
    private String usuario;
    private String metodoEnvio;
    private String producto;
    private String metodoPago;

}
