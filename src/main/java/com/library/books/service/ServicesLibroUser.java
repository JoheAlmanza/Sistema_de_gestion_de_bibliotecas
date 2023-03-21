package com.library.books.service;

import com.library.books.entity.Book;
import com.library.books.entity.User;

import java.util.List;

public interface ServicesLibroUser {

    public List<Book> listarLibro();
    public Book agregarlibro(Book agregarL);

    public Book encontrarLibro( Integer id);
    public String deleteLibro( Integer id);
    public void actualizarLibro(  Book lib, Integer id);

    public List<User> listarUsuario();
    public User agregarUsuario(User agregarU);

    public User encontrarUsuario( Integer id);
    public String deleteUsuario( Integer id);
    public void actualizarUser( User use, Integer id);
    public void actualizarMulta(User use, Integer id);

}
