package com.example.LiterAlura.Models.Libro;

import com.example.LiterAlura.Models.Autor.Autor;
import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libros {
    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idiomas;
    private Integer totalDescargas;

    //Constructores
    public Libros() {
    }

    public Libros(DatosLibro datosLibro, Autor autor){
        this.titulo = datosLibro.titulo();
        this.autor = autor;
        this.idiomas = datosLibro.idiomas().get(0);
        this.totalDescargas = datosLibro.totalDescargas();
    }

    //Getters and Setterts
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return """
                ================
                     LIBROS
                ================
                Título: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                """.formatted(
                        titulo,
                        autor.getNombre(),
                        idiomas,
                        totalDescargas
        );
    }
}
