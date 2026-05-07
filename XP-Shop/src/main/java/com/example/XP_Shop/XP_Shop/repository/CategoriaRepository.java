package com.example.XP_Shop.XP_Shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.XP_Shop.XP_Shop.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
}
