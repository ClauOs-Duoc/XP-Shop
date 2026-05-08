package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.ComunaDTO;
import com.example.XP_Shop.XP_Shop.repository.ComunaRepository;
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
    
}
