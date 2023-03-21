package com.library.books.service;

import com.library.books.entity.Book;
import com.library.books.entity.User;
import com.library.books.exception.DatoNoEncontradoException;
import com.library.books.repository.BooksRepository;
import com.library.books.repository.PrestamoRepository;
import com.library.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImplServicesBookUser implements ServicesLibroUser {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Book> listarLibro() {
        return this.booksRepository.findAll();
    }

    @Override
    public Book agregarlibro(Book agregarL) {
        agregarL.setEstado("DISPONIBLE");
        return booksRepository.save(agregarL);
    }

    @Override
    public Book encontrarLibro( Integer id) {
        Optional<Book> libro = booksRepository.findById(id);
        if( libro.isEmpty() ){
            throw new DatoNoEncontradoException("Libro no registrado");
        }else{
            return  booksRepository.findById(id).orElse(null);
        }}




    @Override
    public String deleteLibro( Integer id) {
         booksRepository.deleteAllById(Collections.singleton(id));
         return "Libro eliminado con éxito";
    }

    @Override
    public void actualizarLibro( Book books, Integer id) {
        Optional<Book> libro = booksRepository.findById(id);
        if(libro.isPresent()){
            Book  _book = libro.get();
            _book.setIdLibros(libro.get().getIdLibros());
            _book.setTitulo(books.getTitulo());
            _book.setEstado(libro.get().getEstado());
            booksRepository.save(_book);
        }else {
            throw new DatoNoEncontradoException("Libro no registrado");
        }


    }
    public void estadoLibro( Integer id) {
        Optional<Book> libro = booksRepository.findById(id);
        if(libro.isPresent()){
            Book  _book = libro.get();
            _book.setEstado("NO DISPONIBLE");
            booksRepository.save(_book);

    }else {
            throw new DatoNoEncontradoException("Libro no registrado");
        }
    }
    public void estadoLibrPrestado( Integer id) {
        Optional<Book> libro = booksRepository.findById(id);
        if(libro.isPresent()){
            Book  _book = libro.get();
            _book.setEstado("DISPONIBLE");
            booksRepository.save(_book);

        }else {
            throw new DatoNoEncontradoException("Libro no registrado");
        }
    }

    @Override
    public List<User> listarUsuario() {

        return  this.userRepository.findAll();
    }

    @Override
    public User agregarUsuario(User agregarU) {
        agregarU.setPrestamos(0);
        agregarU.setMulta("CERO MULTAS");
        return userRepository.save(agregarU);
    }

    @Override
    public User encontrarUsuario( Integer id) {
        Optional<User> usuario = userRepository.findById(id);
        if( usuario.isEmpty()){
            throw new DatoNoEncontradoException("Usuario no registrado");
        }else{
        return  userRepository.findById(id).orElse(null);
    }}

    @Override
    public void actualizarUser(User use, Integer id) {

        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
              User  _use = usuario.get();
              _use.setPrestamos(usuario.get().getPrestamos());
            _use.setNombre(use.getNombre());
            _use.setApellido(use.getApellido());
            _use.setTelefono(use.getTelefono());
          _use.setMulta(usuario.get().getMulta());
            userRepository.save(_use);
        }else {
            throw new DatoNoEncontradoException("Usuario no registrado");
        }

    }
    /*public void estadoMulta( Integer id) {
        Optional<User> prestamoUser = userRepository.findById(id);
        if(prestamoUser.isPresent()){
            User  _user = prestamoUser.get();
            _user.setMulta("MULTADO");
            userRepository.save(_user);

        }else {
            throw new DatoNoEncontradoException("Libro no registrado");
        }}*/

    public void estadoPrestamo( Integer id) {
        Optional<User> prestamoUser = userRepository.findById(id);
        if(prestamoUser.isPresent()){
            User  _user = prestamoUser.get();
            _user.setPrestamos(_user.getPrestamos() + 1);
            userRepository.save(_user);

        }else {
            throw new DatoNoEncontradoException("Libro no registrado");
        }}
    public void estadoPrestamoD( Integer id) {
        Optional<User> prestamoUser = userRepository.findById(id);
        if(prestamoUser.isPresent()){
            User  _user = prestamoUser.get();
            _user.setPrestamos(_user.getPrestamos() - 1);
            userRepository.save(_user);

        }else {
            throw new DatoNoEncontradoException("Libro no registrado");
        }}

        @Override
    public void actualizarMulta(User use, Integer id) {

        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            User  _use = usuario.get();
            _use.setIdUsuarios(usuario.get().getIdUsuarios());
            _use.setNombre(usuario.get().getNombre());
            _use.setApellido(usuario.get().getApellido());
            _use.setTelefono(usuario.get().getTelefono());
            _use.setMulta(use.getMulta());
            userRepository.save(_use);
        }else {
            throw new DatoNoEncontradoException("Usuario no registrado");
        }

    }

    @Override
    public String deleteUsuario( Integer id) {
        userRepository.deleteAllById(Collections.singleton(id));
        return "Usuario eliminado con éxito";
    }


}

