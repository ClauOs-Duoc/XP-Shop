package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.ComunaDTO;
import com.example.XP_Shop.XP_Shop.repository.ComunaRepository;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.model.Comuna;
import com.example.XP_Shop.XP_Shop.model.Usuario;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    private ComunaDTO convertirComunaDTO(Comuna comuna){
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setNombreRegion(comuna.getRegion().getNombreRegion());

        if (comuna.getUsuario() != null){
            List<String> usuarios = new ArrayList<>();
            for(Usuario usuario : comuna.getUsuario()){
                usuarios.add(usuario.getNombreUsuario());
            }
            dto.setNombreUsuarios(usuarios);
        }

        return dto;
    }

    public List<Comuna> ListarComuna() {
        return comunaRepository.findAll();
    }

    public Comuna BuscarComunaPorId(Integer id) {
        return comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La comuna no existe en los registros!"));
    }

    public Comuna GuardarComuna(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna ActualizarComuna(Integer id, Comuna comuna) {
        Comuna comunaExistente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La comuna no existe en los registros!"));

        if (comuna.getNombreComuna() != null) {
            comunaExistente.setNombreComuna(comuna.getNombreComuna());
        }
        if (comuna.getRegion() != null) {
            comunaExistente.setRegion(comuna.getRegion());
        }
        
        return comunaRepository.save(comunaExistente);
    }
    
    public String EliminarComuna(Integer id) {
        try {
            Comuna comuna = comunaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La comuna con ID " 
                            + id + " no existe."));

            comunaRepository.delete(comuna);
            return "La comuna ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
