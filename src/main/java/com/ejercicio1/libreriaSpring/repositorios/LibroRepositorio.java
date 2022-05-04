
package com.ejercicio1.libreriaSpring.repositorios;


import com.ejercicio1.libreriaSpring.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//En los repositorios tenemos todo lo que es CRUD - Create, Remove, Update, Delete y Save en pleno contacto con la BD
//Trabajan con las entidades para que persistan en la BD. 
//Los repos no son clases, sino que son interfaces que heredan metodos de otras interfaces 
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
//    @Query("SELECT a FROM Libro a WHERE a.titulo LIKE :titulo")
//    public Libro buscarPorTitulo(@Param("titulo") String titulo);
//    
//     @Query("SELECT a FROM Libro a WHERE a.id LIKE :id")
//    public Libro buscarPorId(@Param("id") String id);
//    
//     @Query("SELECT a FROM Libro a JOIN Autor b WHERE b.nombre LIKE :nombre")
//    public Libro buscarPorNombreAutor(@Param("nombre") String nombre);
    
    @Query("SELECT a FROM Editorial a WHERE a.id= :PARAMETRO")
public List <Libro> buscarPorId (@Param("PARAMETRO") String id);
    
}
