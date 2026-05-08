package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.MarcaDTO;
import com.example.XP_Shop.XP_Shop.repository.MarcaRepository;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.model.Marca;
import com.example.XP_Shop.XP_Shop.model.Marcas;

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
        
        if (marca.getMarcas() != null){
            List<Integer> idMarcas = new ArrayList<>();
            for(Marcas marcas : marca.getMarcas()){
                idMarcas.add(marcas.getIdMarcas());
            }
            dto.setMarcas(idMarcas);
        }
        return dto;
    }
    
}
