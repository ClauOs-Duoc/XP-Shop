package com.example.XP_Shop.XP_Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.XP_Shop.XP_Shop.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer>{
    
}
