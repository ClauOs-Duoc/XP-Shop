package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.CategoriaDTO;
import com.example.XP_Shop.XP_Shop.model.Categoria;
import com.example.XP_Shop.XP_Shop.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private CategoriaDTO convertirCategoriaADTO(Categoria categoria){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombreCategoria(categoria.getNombreCategoria());
        dto.setIdCategorias(categoria.getCategorias().getIdCategorias());

        return dto;
    }

    public List<CategoriaDTO> listarCategoria() {
        return categoriaRepository.findAll().stream()
                    .map(this::convertirCategoriaADTO)
                    .toList();
    }
    
    public CategoriaDTO buscarCategoriaPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return convertirCategoriaADTO(categoria);
    }

    public CategoriaDTO guardarCategoria(Categoria categoria) {
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertirCategoriaADTO(savedCategoria);
    }

    public CategoriaDTO actualizarCategoria(Integer id, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La categoria no existe."));
        if (categoria.getNombreCategoria() != null) {
            categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
        }
        if (categoria.getCategorias() != null) {
            categoriaExistente.setCategorias(categoria.getCategorias());
        }

        Categoria updatedCategoria = categoriaRepository.save(categoriaExistente);
        return convertirCategoriaADTO(updatedCategoria);
    }

    public Void eliminarCategoria(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar la categoria con ID " + id + " no existe."));
        categoriaRepository.delete(categoria);
        return null;
    }
}
