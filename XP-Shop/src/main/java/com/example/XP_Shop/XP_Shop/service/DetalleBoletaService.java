package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.model.DetalleBoleta;
import com.example.XP_Shop.XP_Shop.repository.DetalleBoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetalleBoletaService {

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    public List<DetalleBoleta> listarDetalleBoleta() {
        return detalleBoletaRepository.findAll();
    }

    public DetalleBoleta BuscarDetalleBoletaPorId(Long id) {
        return detalleBoletaRepository.findById(id).orElseThrow(() -> new RuntimeException("El detalle de boleta no existe."));
    }

    public DetalleBoleta GuardarDetalleBoleta(DetalleBoleta detalleBoleta) {
        return detalleBoletaRepository.save(detalleBoleta);
    }

    public DetalleBoleta ActualizarDetalleBoleta(Long id, DetalleBoleta detalleBoleta) {
        DetalleBoleta detalleExistente = detalleBoletaRepository.findById(id).orElseThrow(() -> new RuntimeException("El detalle de boleta no existe."));

        if (detalleBoleta.getCantidad() != null) {
            detalleExistente.setCantidad(detalleBoleta.getCantidad());
        }
        if (detalleBoleta.getSubtotal() != null) {
            detalleExistente.setSubtotal(detalleBoleta.getSubtotal());
        }
        if (detalleBoleta.getBoleta() != null) {
            detalleExistente.setBoleta(detalleBoleta.getBoleta());
        }
        if (detalleBoleta.getProducto() != null) {
            detalleExistente.setProducto(detalleBoleta.getProducto());
        }

        return detalleBoletaRepository.save(detalleExistente);
    }

    public String EliminarDetalleBoleta(Long id) {
        try {
            DetalleBoleta detalle = detalleBoletaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se puede eliminar el detalle de la boleta con ID " + id + " no existe."));
            detalleBoletaRepository.delete(detalle);
            return "El detalle de boleta ha sido eliminado correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
