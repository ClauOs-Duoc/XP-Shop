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
    
}
