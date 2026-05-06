package com.example.XP_Shop.XP_Shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank (message = "Es obligatorio llenar este apartado")
    @Size(min = 2, max = 100, message = "Debe llenar el apartado con entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreUsuario;

    @NotBlank (message = "Es obligatorio llenar este apartado")
    @Size(min = 10, max = 100, message = "Debe llenar el apartado con entre 10 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String correo;

    @NotBlank (message = "Es obligatorio llenar este apartado")
    @Size(min = 10, max = 30, message = "Debe llenar el apartado con entre 10 y 30 caracteres")
    @Column(nullable = false, length = 30)
    private String fechaNacimiento;
    
}
