package com.example.XP_Shop.XP_Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.XP_Shop.XP_Shop.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer>{
    
}
