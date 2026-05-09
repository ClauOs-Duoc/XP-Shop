package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
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
            List<String> nombresComunas = new ArrayList<>();
            for(Comuna comuna : region.getComunas()){
                nombresComunas.add(comuna.getNombreComuna());
            }
            dto.setNombreComunas(nombresComunas);
        }

        return dto;
    }

    public List<Region> ListarRegion() {
        return regionRepository.findAll();
    }

    public Region BuscarRegionPorId(Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La región no existe en los registros!"));
    }

    public Region GuardarRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region ActualizarRegion(Integer id, Region region) {
        Region regionExistente = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La región no existe en los registros!"));

        if (region.getNombreRegion() != null) {
            regionExistente.setNombreRegion(region.getNombreRegion());
        }

        return regionRepository.save(regionExistente);
    }

    public String EliminarRegion(Integer id) {
        try {
            Region region = regionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La región con ID " 
                            + id + " no existe."));

            regionRepository.delete(region);
            return "La región ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
