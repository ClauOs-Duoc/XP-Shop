package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.model.Usuario;
import com.example.XP_Shop.XP_Shop.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> ListarUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario BuscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe."));
    }

    public Usuario GuardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public Usuario ActualizarUsuario(Integer id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe."));

        if (usuario.getNombreUsuario() != null) {
            usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
        }
        if (usuario.getCorreo() != null) {
            usuarioExistente.setCorreo(usuario.getCorreo());
        }
        if (usuario.getFechaNacimiento() != null) {
            usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
        }
        if (usuario.getRegion() != null) {
            usuarioExistente.setRegion(usuario.getRegion());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    public String EliminarUsuario(Integer id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se puede elminar el usuario con ID " + id + " no existe."));
            usuarioRepository.delete(usuario);
            return "El usuario ha sido eliminado correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
