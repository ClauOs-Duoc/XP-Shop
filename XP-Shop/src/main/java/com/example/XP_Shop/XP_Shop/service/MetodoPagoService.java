package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.MetodoPagoDTO;
import com.example.XP_Shop.XP_Shop.repository.MetodoPagoRepository;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.model.MetodoPago;

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

    public List<MetodoPago> ListarMetodoPago() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago BuscarMetodoPagoPorId(Integer id) {
        return metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡El método de pago no existe en los registros!"));
    }

    public MetodoPago GuardarMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago ActualizarMetodoPago(Integer id, MetodoPago metodoPago) {
        MetodoPago metodoExistente = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡El método de pago no existe en los registros!"));

        if (metodoPago.getNombreMetodoPago() != null) {
            metodoExistente.setNombreMetodoPago(metodoPago.getNombreMetodoPago());
        }

        return metodoPagoRepository.save(metodoExistente);
    }

    public String EliminarMetodoPago(Integer id) {
        try {
            MetodoPago metodoPago = metodoPagoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El método de pago con ID " 
                            + id + " no existe."));

            metodoPagoRepository.delete(metodoPago);
            return "El método de pago ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
