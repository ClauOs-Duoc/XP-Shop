package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.MetodoEnvioDTO;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.model.MetodoEnvio;
import com.example.XP_Shop.XP_Shop.repository.MetodoEnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    private MetodoEnvioDTO convertirMetodoEnvioADTO(MetodoEnvio metodoEnvio){
        MetodoEnvioDTO dto = new MetodoEnvioDTO();
        dto.setIdMetodoEnvio(metodoEnvio.getIdMetodoEnvio());
        dto.setNombreMetodoEnvio(metodoEnvio.getNombreMetodoEnvio());
        
        if (metodoEnvio.getBoletas() != null){
            List<Integer> idBoletas = new ArrayList<>();
            for(Boleta boleta : metodoEnvio.getBoletas()){
                idBoletas.add(boleta.getIdBoleta());
            }
            dto.setBoletas(idBoletas);
        }

        return dto;
    }

    public List<MetodoEnvioDTO> listarMetodoEnvio() {
        return metodoEnvioRepository.findAll().stream()
                    .map(this::convertirMetodoEnvioADTO)
                    .toList();
    }
    
    public MetodoEnvioDTO buscarMetodoEnvioPorId(Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("MetodoEnvio no encontrado"));
        return convertirMetodoEnvioADTO(metodoEnvio);
    }

    public MetodoEnvioDTO guardarMetodoEnvio(MetodoEnvio metodoEnvio) {
        MetodoEnvio savedMetodoEnvio = metodoEnvioRepository.save(metodoEnvio);
        return convertirMetodoEnvioADTO(savedMetodoEnvio);
    }

    public MetodoEnvioDTO actualizarMetodoEnvio(Integer id, MetodoEnvio metodoEnvio) {
        MetodoEnvio metodoEnvioExistente = metodoEnvioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El metodoEnvio no existe."));
        if (metodoEnvio.getNombreMetodoEnvio() != null) {
            metodoEnvioExistente.setNombreMetodoEnvio(metodoEnvio.getNombreMetodoEnvio());
        }
        if (metodoEnvio.getBoletas() != null) {
            metodoEnvioExistente.setBoletas(metodoEnvio.getBoletas());
        }

        MetodoEnvio updatedMetodoEnvio = metodoEnvioRepository.save(metodoEnvioExistente);
        return convertirMetodoEnvioADTO(updatedMetodoEnvio);
    }

    public Void eliminarMetodoEnvio(Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar el metodoEnvio con ID " + id + " no existe."));
        metodoEnvioRepository.delete(metodoEnvio);
        return null;
    }
}
