package com.library.books.controller;

import com.library.books.entity.Book;
import com.library.books.service.ImplServicesBookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BookController {
    @Autowired
    private ImplServicesBookUser serviceB;
    @GetMapping("/listarlibros")
    public List<Book> listarLibros(){
        List<Book> lista = serviceB.listarLibro();
        return  lista;
    }
    @PostMapping("/agregarlibro")
    public Book agregarL(@RequestBody Book libroNuevo ){
        return serviceB.agregarlibro(libroNuevo);
    }
    @GetMapping("/consultarLibro/{id}")
    public Book consultarlibro(@PathVariable Integer id){

        return serviceB.encontrarLibro(id);
    }
    @PutMapping("/actualizarlibro/{id}")
    public void actualizarLibro(@RequestBody Book libro, @PathVariable Integer id){
    serviceB.actualizarLibro( libro, id);
    }
    @DeleteMapping("/eliminarlibro/{id}")
    public String deleteLibrio(@PathVariable Integer id){
        serviceB.deleteLibro(id);
        return "Libro eliminado con exito";
    }
}
