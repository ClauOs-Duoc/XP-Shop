package com.example.XP_Shop.XP_Shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marcas")
public class Marcas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarcas;

    @ManyToOne
    private Marca marca;

    @ManyToOne
    private Producto producto;

}
