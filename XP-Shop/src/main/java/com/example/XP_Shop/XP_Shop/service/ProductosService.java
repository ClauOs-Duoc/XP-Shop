package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.model.Productos;
import com.example.XP_Shop.XP_Shop.repository.ProductosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    public List<Productos> listaProductos() {
        return productosRepository.findAll();
    }

    public Productos buscarProductosPorId(Integer id) {
        return productosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Los productos no existen."));
    }

    public Productos guardarProductos(Productos productos) {
        return productosRepository.save(productos);
    }

    public Productos actualizarProductos(Integer id, Productos productos) {
        Productos productosExistente = productosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Los productos no existen."));

        if (productos.getDetalleBoletas() != null) {
            productosExistente.setDetalleBoletas(productos.getDetalleBoletas());
        }
        if (productos.getProducto() != null) {
            productosExistente.setProducto(productos.getProducto());
        }

        return productosRepository.save(productosExistente);
    }

    public String eliminarProductos(Integer id) {
        try {
            Productos productos = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar los productos con ID" + id + " no existe."));
            productosRepository.delete(productos);
            return "Los productos han sido eliminados correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
}
