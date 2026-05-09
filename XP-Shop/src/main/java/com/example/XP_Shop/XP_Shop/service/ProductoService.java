package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.repository.ProductoRepository;
import com.example.XP_Shop.XP_Shop.dto.ProductoDTO;
import com.example.XP_Shop.XP_Shop.model.Imagen;
import com.example.XP_Shop.XP_Shop.model.Producto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDTO convertirProductoADTO(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setDescripcionProducto(producto.getDescripcionProducto());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setIdProductos(producto.getProductos().getIdProductos());
        dto.setIdMarcas(producto.getMarcas().getIdMarcas());
        dto.setIdCategorias(producto.getCategorias().getIdCategorias());
        if (producto.getImagenes() != null){
            List<String> imagenes = new ArrayList<>();
            for(Imagen imagen : producto.getImagenes()){
                imagenes.add(imagen.getNombreImagen());
            }
            dto.setImagenes(imagenes);
        }

        return dto;
    }

    public List<ProductoDTO> listarProducto() {
        return productoRepository.findAll().stream()
                    .map(this::convertirProductoADTO)
                    .toList();
    }
    
    public ProductoDTO buscarProductoPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertirProductoADTO(producto);
    }

    public ProductoDTO guardarProductoEnvio(Producto producto) {
        Producto savedProducto = productoRepository.save(producto);
        return convertirProductoADTO(savedProducto);
    }

    public ProductoDTO actualizarProducto(Integer id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El producto no existe."));
        if (producto.getNombreProducto() != null) {
            productoExistente.setNombreProducto(producto.getNombreProducto());
        }
        if (producto.getDescripcionProducto() != null) {
            productoExistente.setDescripcionProducto(producto.getDescripcionProducto());
        }
        if (producto.getPrecio() != null) {
            productoExistente.setPrecio(producto.getPrecio());
        }
        if (producto.getStock() != null) {
            productoExistente.setStock(producto.getStock());
        }
        if (producto.getMarcas() != null) {
            productoExistente.setMarcas(producto.getMarcas());
        }
        if (producto.getCategorias() != null) {
            productoExistente.setCategorias(producto.getCategorias());
        }
        if (producto.getImagenes() != null) {
            productoExistente.setImagenes(producto.getImagenes());
        }
        if (producto.getProductos() != null) {
            productoExistente.setProductos(producto.getProductos());
        }

        Producto updatedProducto = productoRepository.save(productoExistente);
        return convertirProductoADTO(updatedProducto);
    }

    public Void eliminarProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar el producto con ID " + id + " no existe."));
        productoRepository.delete(producto);
        return null;
    }
}
