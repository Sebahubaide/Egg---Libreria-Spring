package com.ejercicio1.libreriaSpring.repositorios;

import com.ejercicio1.libreriaSpring.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {

    
//    //Método para buscar por nombre
//    @Query("SELECT a FROM Autor a WHERE a.nombre :nombre")
//    public Autor buscarPorNombre(@Param("nombre") String nombre);
//
//    
//    //Método para modificar por nombre
//    @Query("UPDATE Autor a SET a.nombre = :nombre")
//    public Autor modificarPorNombre(@Param("nombre") String nombre);
//
//    
//    //Método para dar de baja. NI IDEA SI ESTÁ BIEN
//    @Query("UPDATE a FROM Autor a SET a.alta = false WHERE a.id =:id")
//    public Autor darBaja(@Param("id") String id);
    
    @Query("SELECT a FROM Editorial a WHERE a.id= :PARAMETRO")
public List <Autor> buscarAutorporId (@Param("PARAMETRO") String id);
}
