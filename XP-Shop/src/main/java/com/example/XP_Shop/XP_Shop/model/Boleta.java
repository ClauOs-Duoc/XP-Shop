package com.example.XP_Shop.XP_Shop.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBoleta;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @NotNull(message = "Es obligatorio llenar este apartado")
    @Column(nullable = false)
    private Double totalCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_Id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "metodoEnvio_Id")
    private MetodoEnvio metodoEnvio;

    @ManyToMany
    @JoinColumn(name = "producto_Id")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "metodoPago_Id")
    private MetodoPago metodoPago;
    
}
