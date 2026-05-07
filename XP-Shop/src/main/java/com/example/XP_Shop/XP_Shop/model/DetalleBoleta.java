package com.example.XP_Shop.XP_Shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_boleta")

public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleBoleta;

    @NotNull(message = "Es obligatorio llenar este apartado")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "Es obligatorio llenar este apartado")
    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "boleta_Id")
    private Boleta boleta;

    @ManyToOne
    @JoinColumn(name = "producto_Id")
    private Producto producto;



}
