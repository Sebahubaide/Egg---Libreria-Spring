package com.ejercicio1.libreriaSpring.controladores;

import com.ejercicio1.libreriaSpring.entidades.Autor;
import com.ejercicio1.libreriaSpring.entidades.Editorial;
import com.ejercicio1.libreriaSpring.entidades.Libro;
import com.ejercicio1.libreriaSpring.excepciones.ExcepcionPropia;
import com.ejercicio1.libreriaSpring.servicios.AutorServicio;
import com.ejercicio1.libreriaSpring.servicios.EditorialServicio;
import com.ejercicio1.libreriaSpring.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registroLibro")
    public String registroLibro(ModelMap modelo) {
        List<Autor> autores = autorServicio.mostrarTodos();
        modelo.put("autores", autores);
        List<Editorial> editoriales = editorialServicio.mostrarTodos();
        modelo.put("editoriales", editoriales);
        return "registroLibro.html";
    }

    @PostMapping("/registrarLibro")
    public String registrarLibro(ModelMap modelo, Long isbn, @RequestParam String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, @RequestParam Autor autor, @RequestParam Editorial editorial) {
        try {
            libroServicio.cargar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial, true);
        } catch (ExcepcionPropia e) {
            modelo.put("error", e.getMessage());
            modelo.put("isbn", isbn);
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("ejemplares", ejemplares);
            modelo.put("ejemplaresPrestados", ejemplaresPrestados);
            List<Autor> autores = autorServicio.mostrarTodos();
            modelo.put("autores", autores);
            modelo.put("autor", autor);
            List<Editorial> editoriales = editorialServicio.mostrarTodos();
            modelo.put("editoriales", editoriales);
            modelo.put("editorial", editorial);
        }
        
        return "index";
    }
//            @GetMapping("listarLibro")
//            public String listarLIbro(ModelMap modelo){
//                List<Libro> libros = libroServicio.mostrarTodos();
//                modelo.put("libros", libros);
//                return "ListarLibro.html";
//            }
    
    
}
