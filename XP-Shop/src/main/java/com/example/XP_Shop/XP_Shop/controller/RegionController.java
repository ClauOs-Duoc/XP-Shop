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

import com.example.XP_Shop.XP_Shop.dto.RegionDTO;
import com.example.XP_Shop.XP_Shop.model.Region;
import com.example.XP_Shop.XP_Shop.service.RegionService;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> todasLasRegiones() {
        List<RegionDTO> region = regionService.listarRegion();
        if (region.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> buscarPorId(Integer id){
        try {
            RegionDTO region = regionService.nuscarRegionPorId(id);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Region> agregarRegion(@RequestBody Region region) {
        try {
            regionService.guardarRegion(region);
            return new ResponseEntity<>(region, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> editarRegiom(@PathVariable Integer id, @RequestBody Region region) {
        try {
            regionService.guardarRegion(region);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizarRegion(@PathVariable Integer id, @RequestBody Region region){
        try{
            regionService.actualizarRegion(id, region);
            return new ResponseEntity<>(region, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegion(@PathVariable Integer id) {
        try {
            regionService.eliminarRegion(id);
            return new ResponseEntity<>("Eliminado con exito", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
