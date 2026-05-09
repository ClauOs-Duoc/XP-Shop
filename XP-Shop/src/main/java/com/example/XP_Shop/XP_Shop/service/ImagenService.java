package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.ImagenDTO;
import com.example.XP_Shop.XP_Shop.repository.ImagenRepository;
import com.example.XP_Shop.XP_Shop.model.Imagen;
import com.example.XP_Shop.XP_Shop.model.Marcas;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    private ImagenDTO convertirImagenADTO(Imagen imagen){
        ImagenDTO dto = new ImagenDTO();
        dto.setIdImagen(imagen.getIdImagen());
        dto.setNombreImagen(imagen.getNombreImagen());
        dto.setIdProducto(imagen.getProducto().getIdProducto());

        if (imagen.getImagenes() != null){ // no se bien como hacer este, quien haya hecho el atributo de imagenes en la clase imagen que lo haga ya que no se su proposito
            List<Integer> idMarcas = new ArrayList<>();
            for(Marcas marcas : marca.getMarcas()){
                idMarcas.add(marcas.getIdMarcas());
            }
            dto.setMarcas(idMarcas);
        }
        return dto;
    }

    public List<Imagen> ListarImagen() {
        return imagenRepository.findAll();
    }

    public Imagen buscarImagenPorId(Integer id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La imagen no existe en los registros!"));
    }

    public Imagen GuardarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }
    
    public Imagen ActualizarImagen(Integer id, Imagen imagen) {
        Imagen imagenExistente = imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La imagen no existe en los registros!"));

        if (imagen.getNombreImagen() != null) {
            imagenExistente.setNombreImagen(imagen.getNombreImagen());
        }
        if (imagen.getProducto() != null) {
            imagenExistente.setProducto(imagen.getProducto());
        }

        return imagenRepository.save(imagenExistente);
    }

    public String EliminarImagen(Integer id) {
        try {
            Imagen imagen = imagenRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La imagen con ID " 
                            + id + " no existe."));

            imagenRepository.delete(imagen);
            return "La imagen ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
