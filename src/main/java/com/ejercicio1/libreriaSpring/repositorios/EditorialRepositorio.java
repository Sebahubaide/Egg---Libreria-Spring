package com.ejercicio1.libreriaSpring.repositorios;

import com.ejercicio1.libreriaSpring.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

//    @Query("SELECT a FROM Editorial a WHERE a.nombre LIKE :nombre")
//    public Editorial buscarPorNombre(@Param("nombre") String nombre);
//
//    @Query("UPDATE a FROM Editorial a WHERE a.nombre = :nombre")
//    public Editorial modificarPorNombre(@Param("nombre") String nombre);
    
@Query("SELECT a FROM Editorial a WHERE a.id= :PARAMETRO")
public List <Editorial> buscarEditorialporId (@Param("PARAMETRO") String id);

    
    
}
