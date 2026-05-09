package com.example.XP_Shop.XP_Shop.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductos;

    @OneToMany(mappedBy = "producto")
    @JoinColumn(name = "producto_id")
    private List<Producto> producto;

    @OneToMany(mappedBy = "detalleBoleta")
    @JoinColumn(name = "detalleBoleta_id")
    private List<DetalleBoleta> detalleBoletas;

}
