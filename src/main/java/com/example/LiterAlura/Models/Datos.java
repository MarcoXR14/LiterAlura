package com.example.LiterAlura.Models;

import com.example.LiterAlura.Models.Libro.DatosLibro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("results")
        List<DatosLibro> resultados
) {

}
