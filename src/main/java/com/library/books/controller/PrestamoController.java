package com.library.books.controller;

import com.library.books.entity.Prestamo;
import com.library.books.service.ImplServicesPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrestamoController {
    @Autowired
    private ImplServicesPrestamos service;

    @GetMapping("/listarprestamo")
    public List<Prestamo> listarPrestamo(){

        List<Prestamo> lista = service.listarPrestamo();
        return  lista;
    }
    @PostMapping("/agregarprestamo/{idUser}/{idBook}")
    public void agregarPrestamo(@PathVariable Integer idUser, @PathVariable Integer idBook)  {
        service.agregarPrestamo(idUser, idBook);
    }

    @GetMapping("/buscarP/{id}")
    public Prestamo buscarPrest(@PathVariable Integer id){
        return service.encontrarPrestamo(id);
    }
    @PutMapping("/registrardevolucion/{id}/{iduser}/{idbook}")
    public String registroDevolucionLibro(@RequestBody Prestamo devolucion, @PathVariable Integer id, @PathVariable Integer iduser, @PathVariable Integer idbook){
        service.registroDevolucionLibro(devolucion, id, iduser, idbook);
        return "Actualizado";
    }

    @DeleteMapping("/eliminarprestamo/{id}")
    public String deletePrestamo(@PathVariable Integer id ){
        service.deletePrestamo(id);

        return "Prestamo eliminado con exito";
    }
}
