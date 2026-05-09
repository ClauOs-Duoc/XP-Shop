package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.model.Categorias;
import com.example.XP_Shop.XP_Shop.repository.CategoriasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> listaCategorias() {
        return categoriasRepository.findAll();
    }

    public Categorias buscarCategoriasPorId(Integer id) {
        return categoriasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Las categorias no existen."));
    }

    public Categorias guardarCategorias(Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    public Categorias actualizarCategorias(Integer id, Categorias categorias) {
        Categorias categoriasExistente = categoriasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Las categorias no existen."));

        if (categorias.getCategoria() != null) {
            categoriasExistente.setCategoria(categorias.getCategoria());
        }
        if (categorias.getProducto() != null) {
            categoriasExistente.setProducto(categorias.getProducto());
        }

        return categoriasRepository.save(categoriasExistente);
    }

    public String eliminarCategorias(Integer id) {
        try {
            Categorias categorias = categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar las categorias con ID" + id + " no existen."));
            categoriasRepository.delete(categorias);
            return "La categoria ha sido eliminada correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
}
