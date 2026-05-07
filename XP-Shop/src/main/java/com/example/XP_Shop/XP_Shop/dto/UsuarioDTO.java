package com.example.XP_Shop.XP_Shop.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombreUsuario;
    private String correo;
    private LocalDate fechaNaciomiento;
    private List<Integer> idBoleta;
    private String nombreComuna;


}
