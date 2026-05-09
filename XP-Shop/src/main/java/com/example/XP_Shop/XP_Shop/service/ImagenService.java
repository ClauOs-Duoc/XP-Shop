package com.example.XP_Shop.XP_Shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.dto.ImagenDTO;
import com.example.XP_Shop.XP_Shop.repository.ImagenRepository;
import com.example.XP_Shop.XP_Shop.model.Imagen;

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

        if(imagen.getProducto() != null){
            dto.setIdProducto(imagen.getProducto().getIdProducto());
        }
        return dto;
    }

    public List<ImagenDTO> listarImagen() {
        List<Imagen> imagenes = imagenRepository.findAll();
        List<ImagenDTO> dtoList = new ArrayList<>();

        for (Imagen imagen : imagenes) {
            dtoList.add(convertirImagenADTO(imagen));
        }
        return dtoList;
    }

    public ImagenDTO buscarImagenPorId(Integer id) {
        Imagen imagen = imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La imagen no existe"));
        return convertirImagenADTO(imagen);
    }

    public ImagenDTO guardarImagen(Imagen imagen) {
        Imagen savedImagen = imagenRepository.save(imagen);
        return convertirImagenADTO(savedImagen);
    }

    public ImagenDTO actualizarImagen(Integer id, Imagen imagen) {
        Imagen imagenExistente = imagenRepository.findById(id).orElseThrow(() -> new RuntimeException("La imagen no existe en los registros"));
        if (imagen.getNombreImagen() != null) {
            imagenExistente.setNombreImagen(imagen.getNombreImagen());
        }
        if (imagen.getProducto() != null) {
            imagenExistente.setProducto(imagen.getProducto());
        }

        Imagen updatedImagen = imagenRepository.save(imagenExistente);
        return convertirImagenADTO(updatedImagen);
    }

    public Void eliminarImagen(Integer id) {

        Imagen imagen = imagenRepository.findById(id).orElseThrow(() -> new RuntimeException("No se puede eliminar la imagen con ID " + id + " no existe."));
        imagenRepository.delete(imagen);
        return null;
    
    }
}
