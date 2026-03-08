package com.example.LiterAlura.Repository;

import com.example.LiterAlura.Models.Libro.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTituloContainsIgnoreCase(String titulo);

    List<Libros> findByIdiomas(String idioma);

    @Query("SELECT l FROM Libros l ORDER BY l.totalDescargas DESC LIMIT 10")
    List<Libros> top10Libros();}
