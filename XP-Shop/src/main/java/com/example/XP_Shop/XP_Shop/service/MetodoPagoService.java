package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.MetodoPagoDTO;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.model.MetodoPago;
import com.example.XP_Shop.XP_Shop.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    private MetodoPagoDTO convertirMetodoPagoADTO(MetodoPago metodoPago){
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setIdMetodoPago(metodoPago.getIdMetodoPago());
        dto.setNombreMetodoPago(metodoPago.getNombreMetodoPago());

        if (metodoPago.getBoletas() != null){
            List<Integer> idBoletas = new ArrayList<>();
            for(Boleta boleta : metodoPago.getBoletas()){
                idBoletas.add(boleta.getIdBoleta());
            }
            dto.setBoletas(idBoletas);
        }

        return dto;
    }

    public List<MetodoPagoDTO> listarMetodoPago() {
        return metodoPagoRepository.findAll().stream()
                    .map(this::convertirMetodoPagoADTO)
                    .toList();
    }
    
    public MetodoPagoDTO buscarMetodoPagoPorId(Integer id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("MetodoPago no encontrado"));
        return convertirMetodoPagoADTO(metodoPago);
    }

    public MetodoPagoDTO guardarMetodoPago(MetodoPago metodoPago) {
        MetodoPago savedMetodoPago = metodoPagoRepository.save(metodoPago);
        return convertirMetodoPagoADTO(savedMetodoPago);
    }

    public MetodoPagoDTO actualizarMetodoPago(Integer id, MetodoPago metodoPago) {
        MetodoPago metodoPagoExistente = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El metodoPago no existe."));
        if (metodoPago.getNombreMetodoPago() != null) {
            metodoPagoExistente.setNombreMetodoPago(metodoPago.getNombreMetodoPago());
        }
        if (metodoPago.getBoletas() != null) {
            metodoPagoExistente.setBoletas(metodoPago.getBoletas());
        }

        MetodoPago updatedMetodoPago = metodoPagoRepository.save(metodoPagoExistente);
        return convertirMetodoPagoADTO(updatedMetodoPago);
    }

    public Void eliminarMetodoPago(Integer id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar el metodoPago con ID " + id + " no existe."));
        metodoPagoRepository.delete(metodoPago);
        return null;
    }
}
