package com.example.XP_Shop.XP_Shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.XP_Shop.XP_Shop.model.Boleta;
import com.example.XP_Shop.XP_Shop.repository.BoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> ListarBoleta() {
        return boletaRepository.findAll();
    }

    public Boleta BuscarBoletaPorId(Integer id) {
        return boletaRepository.findById(id).orElseThrow(() -> new RuntimeException("La boleta no existe."));
    }

    public Boleta GuardarBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public Boleta ActualizarBoleta(Integer id, Boleta boleta) {
        Boleta boletaExistente = boletaRepository.findById(id).orElseThrow(() -> new RuntimeException("La boleta no existe."));

        if (boleta.getFechaCompra() != null) {
            boletaExistente.setFechaCompra(boleta.getFechaCompra());
        }
        if (boleta.getTotalCompra() != null) {
            boletaExistente.setTotalCompra(boleta.getTotalCompra());
        }
        if (boleta.getUsuario() != null) {
            boletaExistente.setUsuario(boleta.getUsuario());
        }
        if (boleta.getMetodoEnvio() != null) {
            boletaExistente.setMetodoEnvio(boleta.getMetodoEnvio());
        }
        if (boleta.getMetodoPago() != null) {
            boletaExistente.setMetodoPago(boleta.getMetodoPago());
        }

        return boletaRepository.save(boletaExistente);
    }

    public String EliminarBoleta(Integer id) {
        try {
            Boleta boleta = boletaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se puede eliminar La boleta con ID" + id + " no existe."));
            boletaRepository.delete(boleta);
            return "La boleta ha sido eliminada correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
