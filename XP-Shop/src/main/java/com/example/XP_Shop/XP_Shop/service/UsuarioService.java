package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.UsuarioDTO;
import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.model.Usuario;
import com.example.XP_Shop.XP_Shop.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDTO convertirUsuarioADTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setCorreo(usuario.getCorreo());
        dto.setFechaNaciomiento(usuario.getFechaNacimiento());
        if (usuario.getBoletas() != null){
            List<Integer> boletas = new ArrayList<>();
            for(Boleta boleta : usuario.getBoletas()){
                boletas.add(boleta.getIdBoleta());
            }
            dto.setIdBoleta(boletas);
        }
        dto.setNombreComuna(usuario.getComuna().getNombreComuna());

        return dto;
    }

    public List<UsuarioDTO> ListarUsuario() {
        return usuarioRepository.findAll().stream()
                    .map(this::convertirUsuarioADTO)
                    .toList();
    }
    
    public UsuarioDTO BuscarUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertirUsuarioADTO(usuario);
    }

    public UsuarioDTO guardarUsuario(Usuario usuario) {
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertirUsuarioADTO(savedUsuario);
    }

    public UsuarioDTO actualizarUsuario(Integer id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El usuario no existe."));
        if (usuario.getNombreUsuario() != null) {
            usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
        }
        if (usuario.getCorreo() != null) {
            usuarioExistente.setCorreo(usuario.getCorreo());
        }
        if (usuario.getFechaNacimiento() != null) {
            usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
        }
        if (usuario.getBoletas() != null) {
            usuarioExistente.setBoletas(usuario.getBoletas());
        }
        if (usuario.getComuna() != null) {
            usuarioExistente.setComuna(usuario.getComuna());
        }

        Usuario updatedUsuario = usuarioRepository.save(usuarioExistente);
        return convertirUsuarioADTO(updatedUsuario);
    }

    public Void EliminarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar el usuario con ID " + id + " no existe."));
        usuarioRepository.delete(usuario);
        return null;
    }
}
