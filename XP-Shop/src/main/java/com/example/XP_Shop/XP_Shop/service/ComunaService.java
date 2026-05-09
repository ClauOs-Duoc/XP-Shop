package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.ComunaDTO;
import com.example.XP_Shop.XP_Shop.model.Comuna;
import com.example.XP_Shop.XP_Shop.model.Usuario;
import com.example.XP_Shop.XP_Shop.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    private ComunaDTO convertirComunaADTO(Comuna comuna){
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

    public List<ComunaDTO> listarComuna() {
        return comunaRepository.findAll().stream()
                    .map(this::convertirComunaADTO)
                    .toList();
    }
    
    public ComunaDTO buscarComunaPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comuna no encontrado"));
        return convertirComunaADTO(comuna);
    }

    public ComunaDTO guardarComuna(Comuna comuna) {
        Comuna savedComuna = comunaRepository.save(comuna);
        return convertirComunaADTO(savedComuna);
    }

    public ComunaDTO actualizarComuna(Integer id, Comuna comuna) {
        Comuna comunaExistente = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La comuna no existe."));
        if (comuna.getNombreComuna() != null) {
            comunaExistente.setNombreComuna(comuna.getNombreComuna());
        }
        if (comuna.getRegion() != null) {
            comunaExistente.setRegion(comuna.getRegion());
        }
        if (comuna.getUsuario() != null) {
            comunaExistente.setUsuario(comuna.getUsuario());
        }

        Comuna updatedComuna = comunaRepository.save(comunaExistente);
        return convertirComunaADTO(updatedComuna);
    }

    public Void eliminarComuna(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar la comuna con ID " + id + " no existe."));
        comunaRepository.delete(comuna);
        return null;
    }
}
