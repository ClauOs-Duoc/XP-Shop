package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.DetalleBoletaDTO;
import com.example.XP_Shop.XP_Shop.model.DetalleBoleta;
import com.example.XP_Shop.XP_Shop.repository.DetalleBoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetalleBoletaService {

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    private DetalleBoletaDTO convertirDetalleBoletaADTO(DetalleBoleta detalleBoleta){
        DetalleBoletaDTO dto = new DetalleBoletaDTO();
        dto.setIdDetalleBoleta(detalleBoleta.getIdDetalleBoleta());
        dto.setCantidad(detalleBoleta.getCantidad());
        dto.setSubtotal(detalleBoleta.getSubtotal());
        dto.setBoleta(detalleBoleta.getBoleta().getIdBoleta());
        dto.setProductos(detalleBoleta.getProductos().getIdProductos());

        return dto;
    }

    public List<DetalleBoletaDTO> listarDetalleBoleta() {
        return detalleBoletaRepository.findAll().stream()
                    .map(this::convertirDetalleBoletaADTO)
                    .toList();
    }
    
    public DetalleBoletaDTO buscarDetalleBoletaPorId(Integer id) {
        DetalleBoleta detalleBoleta = detalleBoletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DetalleBoleta no encontrado"));
        return convertirDetalleBoletaADTO(detalleBoleta);
    }

    public DetalleBoletaDTO guardarDetalleBoleta(DetalleBoleta detalleBoleta) {
        DetalleBoleta savedDetalleBoleta = detalleBoletaRepository.save(detalleBoleta);
        return convertirDetalleBoletaADTO(savedDetalleBoleta);
    }

    public DetalleBoletaDTO actualizarDetalleBoleta(Integer id, DetalleBoleta detalleBoleta) {
        DetalleBoleta detalleBoletaExistente = detalleBoletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El metodoEnvio no existe."));
        if (detalleBoleta.getCantidad() != null) {
            detalleBoletaExistente.setCantidad(detalleBoleta.getCantidad());
        }
        if (detalleBoleta.getSubtotal() != null) {
            detalleBoletaExistente.setSubtotal(detalleBoleta.getSubtotal());
        }
        if (detalleBoleta.getProductos() != null) {
            detalleBoletaExistente.setBoleta(detalleBoleta.getBoleta());
        }
        if (detalleBoleta.getBoleta() != null) {
            detalleBoletaExistente.setProductos(detalleBoleta.getProductos());
        }

        DetalleBoleta updatedDetalleBoleta = detalleBoletaRepository.save(detalleBoletaExistente);
        return convertirDetalleBoletaADTO(updatedDetalleBoleta);
    }

    public Void eliminarDetalleBoleta(Integer id) {
        DetalleBoleta detalleBoleta = detalleBoletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar el detalleBoleta con ID " + id + " no existe."));
        detalleBoletaRepository.delete(detalleBoleta);
        return null;
    }
}
