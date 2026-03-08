package com.example.LiterAlura.Principal;

import com.example.LiterAlura.Models.Autor.Autor;
import com.example.LiterAlura.Models.Datos;
import com.example.LiterAlura.Models.Libro.DatosLibro;
import com.example.LiterAlura.Models.Libro.Libros;
import com.example.LiterAlura.Repository.AutorRepository;
import com.example.LiterAlura.Repository.LibroRepository;
import com.example.LiterAlura.Services.ConsumoAPI;
import com.example.LiterAlura.Services.ConvieteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    //Variables y Objetos
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvieteDatos conversor = new ConvieteDatos();
    Scanner scanner = new Scanner(System.in);
    private List<Libros> libros;
    private List<Autor> autor;
    private Optional<Libros> libroBuscado;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    //Constantes
    private final String URL_API = "https://gutendex.com/books/?search=";

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    //Menú de opciones
    public void mostrarMenu(){

        var opcion = -1;

        while (opcion != 0) {
            System.out.println(
                    """
                    Bienvenido a nuestra aplicación de LiterAlura, selecciona una opción:
                    1. Buscar y agregar libro a nuestra colección
                    2. Listar todos los libros de nuestra colección
                    3. Listar a todos los autores registrados
                    4. Listar autores vivos en determinado año
                    5. Listar libros por idioma
                    6. Top 10 libros más descargados
                    
                    0. Salir
                    """);
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.println("Estás saliendo del menú principal, gracias por usar nuestra app :)");
                        break;

                    case 1:
                        busquedaLibro();
                        break;

                    case 2:
                        listarLibros();
                        break;

                    case 3:
                        listarAutores();
                        break;

                    case 4:
                        listarAutoresVivos();
                        break;

                    case 5:
                        listarLibrosPorIdioma();
                        break;

                    case 6:
                        top10LibrosMasDescargados();
                        break;

                    default:
                        System.out.println("Opción no valida, revisa la solicitud");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un valor válido dentro de la opción del menú. " + e);
                scanner.nextLine();
            }
        }
    }




    //Funciones del Switch
    private DatosLibro obtenerDatosLibro(){
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        var nombreLibro = scanner.nextLine();

        var json = consumoAPI.obtenerDatos(URL_API + nombreLibro.replaceAll(" ", "+"));

        Datos resultadoObtenido = conversor.obtenerDatos(json, Datos.class);

        if (resultadoObtenido.resultados().isEmpty()) {
            System.out.println("No se ecnontró ningun libro con ese nombre dentro de Gutendex");
            return null;
        } else {
            System.out.println("Si se encontró el libro");
            return resultadoObtenido.resultados().getFirst();
        }
    }

    private void busquedaLibro(){
        DatosLibro datosLibro = obtenerDatosLibro();

        if(datosLibro == null){
            return;
        }

        Optional<Libros> libroExistente =
                libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());

        if(libroExistente.isPresent()){
            System.out.println("El libro ya existe en la base de datos");
            System.out.println(libroExistente.get());
            return;
        }

        var datosAutor = datosLibro.autor().get(0);

        Optional<Autor> autorExistente = autorRepository.findByNombre(datosAutor.nombre());

        Autor autor;

        if(autorExistente.isPresent()){
            autor = autorExistente.get();
        }else{
            autor = new Autor(datosAutor);
            autorRepository.save(autor);
        }

        Libros libro = new Libros(datosLibro, autor);

        libroRepository.save(libro);

        System.out.println("Libro guardado correctamente:");
        System.out.println(libro);
    }

    private void listarLibros(){
        libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> listaAutores = autorRepository.findAll();

        if (listaAutores.isEmpty()) {
            System.out.println("No hay autores que mostrar :(");
            return;
        }

        listaAutores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingresa el año que deseas buscar: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Autor> listaAutoresVivos = autorRepository.autoresVivosEnAnio(year);

        if (listaAutoresVivos.isEmpty()) {
            System.out.println("No hay autores que mostrar :(");
            return;
        }

        listaAutoresVivos.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingresa el idioma que deseas buscar:
                    es - Español
                    en - Inglés
                    fr - Francés
                    pt - Portugés
                """);

        String idioma = scanner.nextLine();

        List<Libros> librosPorIdioma = libroRepository.findByIdiomas(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No existe un libro en ese idioma");
            return;
        }

        librosPorIdioma.forEach(System.out::println);
    }

    private void top10LibrosMasDescargados() {
        var top10Libros = libroRepository.top10Libros();

        top10Libros.forEach(System.out::println);
    }
}
