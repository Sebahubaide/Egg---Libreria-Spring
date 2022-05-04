package com.ejercicio1.libreriaSpring.servicios;

import com.ejercicio1.libreriaSpring.entidades.Editorial;
import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service //Agregamos la anotacion para decirle que es un servicio
public class EditorialServicio {

    @Autowired //Anotacion para inicializar el objeto del repo
    private EditorialRepositorio editorialRepositorio; //Creamos el objeto 

    public void validar(String nombre) throws ExcepcionPropia {
        if (nombre == null || nombre.isEmpty()) {
            throw new ExcepcionPropia("Ingrese un editorial");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public Editorial guardar(String nombre, Boolean alta) throws ExcepcionPropia {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
         editorial.setAlta(Boolean.TRUE);
        return editorialRepositorio.save(editorial);
    }
    
        @Transactional(readOnly = true)
    public List<Editorial> mostrarTodos() {
        return editorialRepositorio.findAll();
    }

}
