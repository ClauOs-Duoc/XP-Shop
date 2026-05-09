package com.example.XP_Shop.XP_Shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.XP_Shop.XP_Shop.dto.BoletaDTO;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.repository.BoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    private BoletaDTO convertirBoletaADTO(Boleta boleta){
        BoletaDTO dto = new BoletaDTO();
        dto.setIdBoleta(boleta.getIdBoleta());
        dto.setFechaCompra(boleta.getFechaCompra());
        dto.setTotalCompra(boleta.getTotalCompra());
        dto.setMetodoEnvio(boleta.getMetodoEnvio().getNombreMetodoEnvio());
        dto.setMetodoPago(boleta.getMetodoPago().getNombreMetodoPago());
        dto.setDetalleBoleta(boleta.getDetalleBoleta().getIdDetalleBoleta());

        return dto;
    }

    public List<BoletaDTO> listarBoleta() {
        return boletaRepository.findAll().stream()
                    .map(this::convertirBoletaADTO)
                    .toList();
    }
    
    public BoletaDTO buscarBoletaPorId(Integer id) {
        Boleta boleta = boletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
        return convertirBoletaADTO(boleta);
    }

    public BoletaDTO guardarBoleta(Boleta boleta) {
        Boleta savedBoleta = boletaRepository.save(boleta);
        return convertirBoletaADTO(savedBoleta);
    }

    public BoletaDTO actualizarBoleta(Integer id, Boleta boleta) {
        Boleta boletaExistente = boletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La boleta no existe."));
        if (boleta.getFechaCompra() != null) {
            boletaExistente.setFechaCompra(boleta.getFechaCompra());
        }
        if (boleta.getMetodoEnvio() != null) {
            boletaExistente.setMetodoEnvio(boleta.getMetodoEnvio());
        }
        if (boleta.getMetodoPago() != null) {
            boletaExistente.setMetodoPago(boleta.getMetodoPago());
        }
        if (boleta.getTotalCompra() != null) {
            boletaExistente.setTotalCompra(boleta.getTotalCompra());
        }
        if (boleta.getDetalleBoleta() != null) {
            boletaExistente.setDetalleBoleta(boleta.getDetalleBoleta());
        }

        Boleta updatedBoleta = boletaRepository.save(boletaExistente);
        return convertirBoletaADTO(updatedBoleta);
    }

    public Void eliminarBoleta(Integer id) {
        Boleta boleta = boletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar la boleta con ID " + id + " no existe."));
        boletaRepository.delete(boleta);
        return null;
    }

}
