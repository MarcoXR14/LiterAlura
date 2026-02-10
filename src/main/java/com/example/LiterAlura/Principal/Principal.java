package com.example.LiterAlura.Principal;

import java.util.Scanner;

public class Principal {
    //Variables
    Scanner scanner = new Scanner(System.in);

    //Constantes

    //Menú de opciones
    public void mostrarMenu(){
        var opcion = -1;

        while (opcion != 0) {
            System.out.println(
                    """
                    Bienvenido a nuestra aplicación de LiterAlura, selecciona una opción:
                    1. Buscar un libro por título
                    2. Listar todos los libros
                    
                    0. Salir
                    """);
            opcion = scanner.nextInt();
            scanner.nextLine();
        }
    }
}
