package com.library.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="libros")
public class Book implements Serializable {
    private static final int serialVersionUID= 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibros;
    @Column(name="titulo")
    private String titulo;
    @Column(name="estado")
    private String estado;



}
