package com.ejercicio1.libreriaSpring.controladores;

import com.ejercicio1.libreriaSpring.entidades.Libro;
import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.servicios.AutorServicio;
import com.ejercicio1.libreriaSpring.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortalControlador {

    @Autowired
    private AutorServicio autorServicio;
    
     @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
           @GetMapping("listarLibro")
            public String listarLIbro(ModelMap modelo){
                List<Libro> libros = libroServicio.mostrarTodos();
                modelo.addAttribute("libros", libros);
                return "ListarLibro";
            }
            
            @GetMapping("/bajaLibro/{id}")
    public String bajaLibro(ModelMap modelo, @PathVariable String id){
        
        try{
            libroServicio.deshabilitar(id);   
        }catch(ExcepcionPropia ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return "redirect:/listarLibro"; //Redireccionamos a otro metodo del controlador y no a la vista
    }
    
    @GetMapping("/altaLibro/{id}")
    public String altaLibro(ModelMap modelo, @PathVariable String id){
        
        try{
            libroServicio.habilitar(id);   
        }catch(ExcepcionPropia ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            
        }      
        return "redirect:/listarLibro";
    }



//    @GetMapping("/registroAutor")
//    public String registroAutor() {
//        return "RegistroAutor.html";
//    }

//    @GetMapping("/registroLibro")
//    public String registroLibro() {
//        return "registroLibro.html";
//    }

//    @GetMapping("/registroEditorial")
//    public String registroEditorial() {
//        return "RegistroEditorial.html";
//    }

//    @PostMapping("/registrarAutor")
//    public String registrarAutor(@RequestParam String nombre) {
//        try {
////        System.out.println("Nombre:" + nombre);
////        System.out.println("Apellido:" + apellido);
//
//autorServicio.guardar(nombre, true);
//        } catch (ExcepcionPropia ex) {
//            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//            return "RegistroAutor.html";
//        }
//
//        return "index.html";
//    }

}
