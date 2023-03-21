package com.library.books.controller;

import com.library.books.entity.User;
import com.library.books.service.ImplServicesBookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private ImplServicesBookUser serviceU;
    @GetMapping("/listarusuario")
    public List<User> listarLibros(){
    List<User> lista = serviceU.listarUsuario();
    return  lista;
}
    @PostMapping("/agregarusuario")
    public User agregarU(@RequestBody User usuarioN ){

        return serviceU.agregarUsuario(usuarioN);
    }

    @GetMapping("/buscarusuario/{id}")
    public User buscarusuario(@PathVariable Integer id){
        return serviceU.encontrarUsuario(id);
    }
    @PutMapping("/actualizarusuario/{id}")
    public void actualizarusuario( @RequestBody User use, @PathVariable Integer id){

        serviceU.actualizarUser( use, id);

    }
    @PutMapping("/actualizarmulta/{id}")
    public void actualizar( @RequestBody User use, @PathVariable Integer id){

        serviceU.actualizarMulta( use, id);

    }
    @DeleteMapping("/eliminarUsuario/{id}")
    public String deleteUsuario(@PathVariable Integer id){
        serviceU.deleteUsuario(id);
        return "Usuario eliminado con exito";
    }
}
