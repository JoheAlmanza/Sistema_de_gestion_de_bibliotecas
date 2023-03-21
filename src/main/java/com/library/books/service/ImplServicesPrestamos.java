package com.library.books.service;

import com.library.books.entity.Book;
import com.library.books.entity.Prestamo;
import com.library.books.entity.User;
import com.library.books.exception.AccionNoRealizadaException;
import com.library.books.exception.DatoNoEncontradoException;
import com.library.books.exception.MultaPorPrestamoRetrasadoException;
import com.library.books.repository.BooksRepository;
import com.library.books.repository.PrestamoRepository;
import com.library.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import static java.time.Period.between;


@Service

public class ImplServicesPrestamos implements ServicesPrestamo {
    List<Prestamo> dev = new ArrayList<>();
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImplServicesBookUser implServicesBU;
    @Override
    public List<Prestamo> listarPrestamo() {

        return this.prestamoRepository.findAll();
    }
    @Override
    public Prestamo encontrarPrestamo( Integer id) {

      Optional<Prestamo> buscarPrestamo = prestamoRepository.findById(id);
        if(buscarPrestamo.isEmpty()) {
            throw new DatoNoEncontradoException("Prestamo no registrado");
        }else{
            return prestamoRepository.findById(id).orElse(null);
        }
    }
    @Override
    public Prestamo agregarPrestamo(Integer idUser, Integer idBook)  {
        Prestamo prestamo = new Prestamo();
        User usuario = userRepository.findById(idUser).orElse(null);
        implServicesBU.encontrarUsuario(idUser);
        Book libro = booksRepository.findById(idBook).orElse(null);
        implServicesBU.encontrarLibro(idBook);
        if(autorizarPrestamo(libro, usuario) == false){
            prestamo.setIdLibro(libro);
            prestamo.setIdUsuario(usuario);
            prestamo.setFechaPrestamo(LocalDate.now());
            prestamo.setFechaVencimientoP(prestamo.getFechaPrestamo().plusDays(5));
            prestamoRepository.save(prestamo);
          // prestamo.getIdUsuario().setPrestamos(prestamo.getIdUsuario().getPrestamos() );
            implServicesBU.estadoPrestamo ( idUser);
           // prestamo.getIdLibro().setEstado(" NO DISPONIBLE");
            implServicesBU.estadoLibro ( idBook);
        }else{
            throw new AccionNoRealizadaException("No se pudo guardar el prestamo porque el usuario está multado, "+
                    "el libro no está disponible o  el ususario superó el número de prestamos permitidos");
        }



        return prestamoRepository.save(prestamo);}
    public Boolean autorizarPrestamo(Book book, User user){
        boolean autorizado;
        if( book.getEstado().equals("NO DISPONIBLE")  || user.getMulta().equals("MULTADO") || user.getPrestamos() >2){
            autorizado= true;
        }else{
             autorizado= false;
        }
        return autorizado;
    }

    /*@Override
    public Prestamo encontrarPrestamo( Integer id) {
        Optional<Prestamo> buscarPrestamo = prestamoRepository.findById(id);
        if(buscarPrestamo.isEmpty()) {
            throw new DatoNoEncontradoException("Prestamo no registrado");
        }else{
        return prestamoRepository.findById(id).orElse(null);
    }}*/

    public String registroDevolucionLibro(Prestamo prestamoNuevo, Integer id, Integer iduser,  Integer idbook) {
        //BUSCO PRESTAMO POR ID Y AGREGO FECHA DE DEVOLUCIÓN
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(prestamo.isPresent()){
            Prestamo  _prestamo = prestamo.get();
            _prestamo.setIdPrestamo(prestamo.get().getIdPrestamo());
            _prestamo.setFechaDevolucion(prestamoNuevo.getFechaDevolucion());

            //CÁLCULO DIAS DE RETRASO
            Period duration = Period.from(between(_prestamo.getFechaVencimientoP(), prestamoNuevo.getFechaDevolucion()));
            _prestamo.setDiasRetraso((long) duration.getDays());
            if (_prestamo.getDiasRetraso() == 0) {
               // _prestamo.getIdUsuario().setPrestamos(_prestamo.getIdUsuario().getPrestamos());
                implServicesBU.estadoPrestamoD(iduser);
                if (_prestamo.getIdUsuario().getPrestamos() == 0) {
                   _prestamo.getIdUsuario().setMulta("CERO MULTAS");
                    implServicesBU.actualizarUser(_prestamo.getIdUsuario(), iduser);
                }
               // _prestamo.getIdLibro().setEstado("DISPONIBLE");
                implServicesBU.estadoLibrPrestado( idbook);


            }else{
                _prestamo.getIdUsuario().setMulta("MULTADO");
                implServicesBU.actualizarUser(_prestamo.getIdUsuario(), iduser);
                //_prestamo.getIdUsuario().setPrestamos(_prestamo.getIdUsuario().getPrestamos() );
                implServicesBU.estadoPrestamoD( iduser);
               // _prestamo.getIdLibro().setEstado("DISPONIBLE");
                implServicesBU.estadoLibrPrestado( idbook);
                //_prestamo.getIdUsuario().setPrestamos("NO");
                throw new MultaPorPrestamoRetrasadoException("Multado por devolución atrasada");
            }
            prestamoRepository.save(_prestamo);

        }else {
            throw new DatoNoEncontradoException("Prestamo no registrado");}

            /*Period duration = Period.from(between(prestamoNuevo.getFechaVencimientoP(), prestamoNuevo.getFechaDevolucion()));
            prestamoNuevo.setDiasRetraso((long) duration.getDays());
            */

    return "Actualizado";}

    @Override
    public String deletePrestamo( Integer id ) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(prestamo.isEmpty()) {
            throw new DatoNoEncontradoException("Prestamo no registrado");
        }
            prestamoRepository.deleteById(id);
           /* List<Book> books = booksRepository.findAll();
            prestamo.get().getIdLibro().setEstado("DISPONIBLE");
            implServicesBU.actualizarLibro(prestamo.get().getIdLibro(), prestamo.get().getIdPrestamo());*/


    return "Prestamo eliminado con éxito";
    }



}

