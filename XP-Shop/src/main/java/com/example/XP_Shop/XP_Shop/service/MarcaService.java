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


    public List<Marca> listarMarca() {
        return marcaRepository.findAll();
    }

    public Marca BuscarMarcaPorId(Integer id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La marca no existe en los registros!"));
    }

    public Marca GuardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca ActualizarMarca(Integer id, Marca marca) {
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La marca no existe en los registros!"));

        if (marca.getNombreMarca() != null) {
            marcaExistente.setNombreMarca(marca.getNombreMarca());
        }

        return marcaRepository.save(marcaExistente);
    }

    public String EliminarMarca(Integer id) {
        try {
            Marca marca = marcaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La marca con ID " 
                            + id + " no existe."));

            marcaRepository.delete(marca);
            return "La marca ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
