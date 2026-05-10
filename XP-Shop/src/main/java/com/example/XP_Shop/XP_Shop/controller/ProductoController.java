package com.example.XP_Shop.XP_Shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.XP_Shop.XP_Shop.dto.ProductoDTO;
import com.example.XP_Shop.XP_Shop.model.Producto;
import com.example.XP_Shop.XP_Shop.service.ProductoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar(){
        return ResponseEntity.ok(productoService.listarProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            ProductoDTO producto = productoService.buscarProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> guardar(@Valid @RequestBody Producto producto) {
        try {
            ProductoDTO nuevoProducto = productoService.guardarProducto(producto);
            return ResponseEntity.ok(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
