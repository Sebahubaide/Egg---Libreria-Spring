package com.ejercicio1.libreriaSpring.servicios;

import com.ejercicio1.libreriaSpring.entidades.Autor;
import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;
    
    public void validar(String nombre) throws ExcepcionPropia{
    
     if(nombre==null || nombre.isEmpty()) {
     throw new ExcepcionPropia("El nombre del autor no puede ser nulo");   
}
}
    @Transactional
    public Autor guardar(String nombre, Boolean alta) throws ExcepcionPropia {
        validar(nombre);
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);
        return autorRepositorio.save(autor);
    }

    //Método para mostrar todos los autores
    @Transactional(readOnly = true)
    public List<Autor> mostrarTodos() {
        return autorRepositorio.findAll();
    }

    //Método para borrar un autor
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorID(String id) {
        Optional<Autor> optional = autorRepositorio.findById(id);
        if (optional.isPresent()) {
            autorRepositorio.delete(optional.get());
        }
    }
}
