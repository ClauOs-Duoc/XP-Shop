package com.example.XP_Shop.XP_Shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.XP_Shop.XP_Shop.model.Categoria;
import com.example.XP_Shop.XP_Shop.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }
    
    public Categoria BuscarCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La categoría no existe en los registros!"));
    }

    public Categoria GuardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria ActualizarCategoria(Integer id, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La categoría no existe en los registros!"));

        if (categoria.getNombreCategoria() != null) {
            categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
        }

        return categoriaRepository.save(categoriaExistente);
    }

    public String EliminarCategoria(Integer id) {
        try {
            Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La categoría con ID " 
                            + id + " no existe."));

            categoriaRepository.delete(categoria);
            return "La categoría ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
