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

    public List<MetodoEnvio> ListarMetodoEnvio() {
        return metodoEnvioRepository.findAll();
    }
    
    public MetodoEnvio BuscarMetodoEnvioPorId(Integer id) {
        return metodoEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡El método de envío no existe en los registros!"));
    }

    public MetodoEnvio GuardarMetodoEnvio(MetodoEnvio metodoEnvio) {
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public MetodoEnvio ActualizarMetodoEnvio(Integer id, MetodoEnvio metodoEnvio) {
        MetodoEnvio metodoExistente = metodoEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡El método de envío no existe en los registros!"));

        if (metodoEnvio.getNombreMetodoEnvio() != null) {
            metodoExistente.setNombreMetodoEnvio(metodoEnvio.getNombreMetodoEnvio());
        }

        return metodoEnvioRepository.save(metodoExistente);
    }

    public String EliminarMetodoEnvio(Integer id) {
        try {
            MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El método de envío con ID " 
                            + id + " no existe."));

            metodoEnvioRepository.delete(metodoEnvio);
            return "El método de envío ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
