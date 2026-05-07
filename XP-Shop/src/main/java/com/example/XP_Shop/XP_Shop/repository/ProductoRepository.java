package com.example.XP_Shop.XP_Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.XP_Shop.XP_Shop.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
