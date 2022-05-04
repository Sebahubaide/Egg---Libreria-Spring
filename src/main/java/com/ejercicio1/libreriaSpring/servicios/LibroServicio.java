package com.ejercicio1.libreriaSpring.servicios;

import com.ejercicio1.libreriaSpring.entidades.Autor;
import com.ejercicio1.libreriaSpring.entidades.Editorial;
import com.ejercicio1.libreriaSpring.entidades.Libro;
import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//En los servicios validamos los datos a persistir, aplicar la logica del negocio
//Procesa la info y la envia al repo. 

@Service
public class LibroServicio {

    @Autowired //esta anotacion me crea una instancia del repo, inicializa los objetos
    private LibroRepositorio libroRepositorio; //el servicio "recibe" la info de los controladores y la manda al repo para que la procese

    @Transactional(propagation = Propagation.NESTED) //SI HAY UNA TRANSACCION CON LA DB LLEVA SI O SI ESTA NOTACION
    public Libro cargar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Autor autor, Editorial editorial, Boolean alta) throws ExcepcionPropia {
        //Me faltan las validaciones sirven cuando los atributos están vacios o cosas especificas del atributo pero las puedo agregar con un metodo agnostico
       validar(titulo);
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAlta(Boolean.TRUE);
        return libroRepositorio.save(libro); //El metodo save de repositorio persiste en la DB
    }

    public void validar(String titulo) throws ExcepcionPropia {
        if (titulo == null || titulo.isEmpty()) {
            throw new ExcepcionPropia("El titulo no puede ser nulo");
        }
    }

    //Metodo para mostrar todos los registros
    @Transactional(readOnly = true) //Esta funcion del Transactional de read only hace que solo se lea en la base de datos
    public List<Libro> mostrarTodos() {
        return libroRepositorio.findAll();
    }

    //Método para borrar un libro
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<Libro> optional = libroRepositorio.findById(id);
        if (optional.isPresent()) {
            libroRepositorio.delete(optional.get());
        }
    }
    
    //Metodo para dar de baja
    @Transactional (propagation= Propagation.NESTED)
    public void deshabilitar (String id) throws ExcepcionPropia{ // con este metodo deshabilitamos o le damos de baja a un usuario de la base de datos
        
       Optional <Libro> optional = libroRepositorio.findById(id); // con el optional puedo ver si me devuelve un objeto en este caso un libro entonces lo busca y lo borra, sino devuelve una excepcion
        
        if (optional.isPresent()){
            Libro libro= optional.get();
            libro.setAlta(Boolean.FALSE);
            libroRepositorio.save(libro);
        }else{
            throw new ExcepcionPropia ("El libro que se quiere deshabilitar no existe");
        }
    }

    //Metodo para dar de alta
    @Transactional (propagation= Propagation.NESTED)
    public void habilitar (String id) throws ExcepcionPropia{ // con este metodo habilitamos o le damos de baja a un usuario de la base de datos
        
       Optional <Libro> optional = libroRepositorio.findById(id); // con el optional puedo ver si me devuelve un objeto en este caso un libro entonces lo busca y lo borra, sino devuelve una excepcion
        
        if (optional.isPresent()){
            Libro libro= optional.get();
            libro.setAlta(Boolean.TRUE);
            libroRepositorio.save(libro);
        }else{
            throw new ExcepcionPropia ("El libro que se quiere habilitar no existe");
        }
    }


}
