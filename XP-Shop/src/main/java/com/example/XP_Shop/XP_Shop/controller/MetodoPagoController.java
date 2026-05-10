package com.example.XP_Shop.XP_Shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.XP_Shop.XP_Shop.dto.MetodoPagoDTO;
import com.example.XP_Shop.XP_Shop.model.MetodoPago;
import com.example.XP_Shop.XP_Shop.service.MetodoPagoService;

@RestController
@RequestMapping("/api/v1/metodoPago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> todOsLOsMetodoPago() {
        List<MetodoPagoDTO> metodoPago = metodoPagoService.listarMetodoPago();
        if (metodoPago.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(metodoPago, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> buscarPorId(Integer id){
        try {
            MetodoPagoDTO metodoPago = metodoPagoService.buscarMetodoPagoPorId(id);
            return new ResponseEntity<>(metodoPago, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MetodoPago> agregarMetodoPago(@RequestBody MetodoPago metodoPago) {
        try {
            metodoPagoService.guardarMetodoPago(metodoPago);
            return new ResponseEntity<>(metodoPago, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPago> editarRegiom(@PathVariable Integer id, @RequestBody MetodoPago metodoPago) {
        try {
            metodoPagoService.guardarMetodoPago(metodoPago);
            return new ResponseEntity<>(metodoPago, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPago){
        try{
            metodoPagoService.actualizarMetodoPago(id, metodoPago);
            return new ResponseEntity<>(metodoPago, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMetodoPago(@PathVariable Integer id) {
        try {
            metodoPagoService.eliminarMetodoPago(id);
            return new ResponseEntity<>("Eliminado con exito", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
