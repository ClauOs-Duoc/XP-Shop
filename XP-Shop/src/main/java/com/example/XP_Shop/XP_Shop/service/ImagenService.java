package com.example.XP_Shop.XP_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XP_Shop.XP_Shop.repository.ImagenRepository;
import com.example.XP_Shop.XP_Shop.model.Imagen;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> obtenerTodos() {
    return imagenRepository.findAll();
    }

}
