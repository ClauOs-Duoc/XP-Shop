package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.repository.RegionRepository;
import com.example.XP_Shop.XP_Shop.dto.RegionDTO;
import com.example.XP_Shop.XP_Shop.model.Comuna;
import com.example.XP_Shop.XP_Shop.model.Region;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    private RegionDTO convertirRegionADTO(Region region){
        RegionDTO dto = new RegionDTO();

        dto.setIdRegion(region.getIdRegion());

        dto.setNombreRegion(region.getNombreRegion());

        if (region.getComunas() != null){
            List<String> nombres = region.getComunas().stream()
                    .map(Comuna::getNombreComuna)
                    .toList();
            dto.setNombreComunas(nombres);
        }

        return dto;
    }
    
}
