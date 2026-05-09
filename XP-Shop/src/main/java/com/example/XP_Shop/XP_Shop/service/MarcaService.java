package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.MarcaDTO;
import com.example.XP_Shop.XP_Shop.repository.MarcaRepository;
import com.example.XP_Shop.XP_Shop.model.Marca;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaDTO convertirMarcaADTO(Marca marca){
        MarcaDTO dto = new MarcaDTO();
        dto.setIdMarca(marca.getIdMarca());
        dto.setNombreMarca(marca.getNombreMarca());
        dto.setMarcasId(marca.getMarcas().getIdMarcas());

        return dto;
    }

    public List<MarcaDTO> listarMarca() {
        return marcaRepository.findAll().stream()
                    .map(this::convertirMarcaADTO)
                    .toList();
    }
    
    public MarcaDTO buscarMarcaPorId(Integer id) {
        Marca marca = marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Marca no encontrado"));
        return convertirMarcaADTO(marca);
    }

    public MarcaDTO guardarMarca(Marca marca) {
        Marca savedMarca = marcaRepository.save(marca);
        return convertirMarcaADTO(savedMarca);
    }

    public MarcaDTO actualizarMarca(Integer id, Marca marca) {
        Marca marcaExistente = marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El marca no existe."));
        if (marca.getNombreMarca() != null) {
            marcaExistente.setNombreMarca(marca.getNombreMarca());
        }
        if (marca.getMarcas() != null) {
            marcaExistente.setMarcas(marca.getMarcas());
        }

        Marca updatedMarca = marcaRepository.save(marcaExistente);
        return convertirMarcaADTO(updatedMarca);
    }

    public Void eliminarMarca(Integer id) {
        Marca marca = marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar la marca con ID " + id + " no existe."));
        marcaRepository.delete(marca);
        return null;
    }
}
