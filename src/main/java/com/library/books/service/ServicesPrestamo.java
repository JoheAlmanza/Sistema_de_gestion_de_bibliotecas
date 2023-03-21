package com.library.books.service;

import com.library.books.entity.Prestamo;

import java.util.List;

public interface ServicesPrestamo {
    //SERVICIO DE PRESTAMO
    public List<Prestamo> listarPrestamo();
    public Prestamo agregarPrestamo(Integer idUser, Integer idBook) throws Exception;
    public Prestamo encontrarPrestamo( Integer id);
    public String registroDevolucionLibro( Prestamo prestamoNuevo, Integer id, Integer iduser,Integer idbook);
    public String deletePrestamo( Integer id);


}
