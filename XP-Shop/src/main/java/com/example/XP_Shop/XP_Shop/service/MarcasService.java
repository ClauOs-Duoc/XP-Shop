package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.model.Marcas;
import com.example.XP_Shop.XP_Shop.repository.MarcasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcasService {

    @Autowired
    private MarcasRepository marcasRepository;

    public List<Marcas> listaMarcas() {
        return marcasRepository.findAll();
    }

    public Marcas buscarMarcasPorId(Integer id) {
        return marcasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Las marcas no existe."));
    }

    public Marcas guardarMarcas(Marcas marcas) {
        return marcasRepository.save(marcas);
    }

    public Marcas actualizarMarcas(Integer id, Marcas marcas) {
        Marcas marcasExistente = marcasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Las marcas no existe."));

        if (marcas.getMarcas() != null) {
            marcasExistente.setMarcas(marcas.getMarcas());
        }
        if (marcas.getProductos() != null) {
            marcasExistente.setProductos(marcas.getProductos());
        }

        return marcasRepository.save(marcasExistente);
    }

    public String eliminarMarcas(Integer id) {
        try {
            Marcas marcas = marcasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar las marcas con ID" + id + " no existe."));
            marcasRepository.delete(marcas);
            return "Las marcas han sido eliminada correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
}
