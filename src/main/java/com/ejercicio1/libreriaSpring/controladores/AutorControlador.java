package com.ejercicio1.libreriaSpring.controladores;

import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registroAutor")
    public String registroAutor() {
        return "RegistroAutor.html";
    }

    @PostMapping("/registrarAutor")
    public String registrarAutor(@RequestParam String nombre) {
        try {
//        System.out.println("Nombre:" + nombre);
//        System.out.println("Apellido:" + apellido);
            autorServicio.guardar(nombre, true);
        } catch (ExcepcionPropia ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "RegistroAutor.html";
        }
        return "index.html";
    }
}
