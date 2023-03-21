package com.library.books.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="usuario")
public class User implements Serializable {
    private static final int serialVersionUID= 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarios;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="telefono")
    private String telefono;
    @Column(name="multas")
    private String multa;
    @Column(name="prestamos")
    private int prestamos;







}
