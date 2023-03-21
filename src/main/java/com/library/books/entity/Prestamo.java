package com.library.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.Period.between;

@Entity
@Data
@Table(name="prestamo")
public class Prestamo implements Serializable {
    private static final int serialVersionUID= 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestamo;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_prestamo")
    private LocalDate fechaPrestamo;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_vencimientoP")
    private LocalDate fechaVencimientoP;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_devolucion")
    private LocalDate fechaDevolucion;

    @Column(name="dias_retrasoE")
    private Long diasRetraso;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Book idLibro;




    }
