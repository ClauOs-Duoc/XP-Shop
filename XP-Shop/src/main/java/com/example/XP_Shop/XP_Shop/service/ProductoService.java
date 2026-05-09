package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.repository.ProductoRepository;
import com.example.XP_Shop.XP_Shop.model.Producto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> ListarProducto() {
        return productoRepository.findAll();
    }

    public Producto BuscarProductoPorId(Integer id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("El producto no existe."));
    }

    public Producto GuardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto ActualizarProducto(Integer id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("El producto no existe."));

        if (producto.getNombreProducto() != null) {
            productoExistente.setNombreProducto(producto.getNombreProducto());
        }
        if (producto.getPrecio() != null) {
            productoExistente.setPrecio(producto.getPrecio());
        }
        if (producto.getDescripcionProducto() != null) {
            productoExistente.setDescripcionProducto(producto.getDescripcionProducto());
        }
        if (producto.getStock() != null) {
            productoExistente.setStock(producto.getStock());
        }
        if (producto.getMarca() != null) {
            productoExistente.setMarca(producto.getMarca());
        }
        if (producto.getCategoria() != null) {
            productoExistente.setCategoria(producto.getCategoria());
        }

        return productoRepository.save(productoExistente);
    }

    public String EliminarProducto(Integer id) {
        try {
            Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se puede eliminar El producto con ID " + id + " no existe."));
            productoRepository.delete(producto);
            return "El producto ha sido eliminado correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
