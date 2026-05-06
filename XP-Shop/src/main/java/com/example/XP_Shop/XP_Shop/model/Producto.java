package com.example.XP_Shop.XP_Shop.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @NotBlank (message = "Es obligatorio llenar este apartado")
    @Size(min = 2, max = 100, message = "Debe llenar el apartado con entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreProducto;

    @NotNull (message = "Es obligatorio asignar un valor a este apartado")
    @Min(value = 1, message = "El producto no puede tener un precio inferior a 1")
    @Max(value = 20000000, message = "El producto no puede tener un valor superior a 20000000")
    @Column(nullable = false)
    private Integer precio;

    @NotBlank (message = "Es obligatorio llenar este apartado")
    @Size(min = 2, max = 100, message = "Debe llenar el apartado con entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcionProducto;

    @ManyToMany // preguntar al profesor si es many to one o many to many
    @JoinColumn(name = "idMarca")
    private Marca marca;

    @ManyToMany
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @ManyToMany(mappedBy = "productos")
    private List<Imagen> imagenes;
    
}
