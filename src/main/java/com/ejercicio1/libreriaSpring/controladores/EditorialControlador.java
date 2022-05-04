package com.ejercicio1.libreriaSpring.controladores;

import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.servicios.EditorialServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registroEditorial")
    public String registroEditorial() {
        return "RegistroEditorial.html";
    }
    
//    @PostMapping("/registrarEditorial")
//    public String registrarEditorial(@RequestParam String nombre){
//        try {
//            editorialServicio.guardar(nombre, true);
//       } catch (ExcepcionPropia ex) {
//            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//            return "RegistroEditorial.html";
//        }
//        return "index.html";
//    }
    
    
     @PostMapping("/registrarEditorial")
    public String registrarEditorial(@RequestParam String nombre) {
        try {
//        System.out.println("Nombre:" + nombre);
//        System.out.println("Apellido:" + apellido);
            editorialServicio.guardar(nombre, true);
        } catch (ExcepcionPropia ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "RegistroEditorial.html";
        }
        return "index.html";
    }
}
