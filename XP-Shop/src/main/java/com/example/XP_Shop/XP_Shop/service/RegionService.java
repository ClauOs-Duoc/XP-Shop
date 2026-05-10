package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.RegionDTO;
import com.example.XP_Shop.XP_Shop.model.Comuna;
import com.example.XP_Shop.XP_Shop.model.Region;
import com.example.XP_Shop.XP_Shop.repository.RegionRepository;

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
            List<String> nombresComunas = new ArrayList<>();
            for(Comuna comuna : region.getComunas()){
                nombresComunas.add(comuna.getNombreComuna());
            }
            dto.setNombreComunas(nombresComunas);
        }

        return dto;
    }

    public List<RegionDTO> listarRegion() {
        return regionRepository.findAll().stream()
                    .map(this::convertirRegionADTO)
                    .toList();
    }
    
    public RegionDTO buscarRegionPorId(Integer id) {
        Region region = regionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Region no encontrado"));
        return convertirRegionADTO(region);
    }

    public RegionDTO guardarRegion(Region region) {
        Region savedRegion = regionRepository.save(region);
        return convertirRegionADTO(savedRegion);
    }

    public RegionDTO actualizarRegion(Integer id, Region region) {
        Region regionExistente = regionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La region no existe."));
        if (region.getNombreRegion() != null) {
            regionExistente.setNombreRegion(region.getNombreRegion());
        }
        if (region.getComunas() != null) {
            regionExistente.setComunas(region.getComunas());
        }

        Region updatedRegion = regionRepository.save(regionExistente);
        return convertirRegionADTO(updatedRegion);
    }

    public Void eliminarRegion(Integer id) {
        Region region = regionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar la region con ID " + id + " no existe."));
        regionRepository.delete(region);
        return null;
    }
}
