# 📚 Literalura - Catálogo de Libros con API Gutendex

Literalura es una aplicación de consola desarrollada en **Java** que permite buscar libros utilizando la API pública de **Gutendex**, almacenar los resultados en una base de datos y consultar diferentes estadísticas sobre los libros y autores registrados.

Este proyecto forma parte del challenge **Literalura** donde se trabaja con consumo de APIs, persistencia de datos y consultas usando **Spring Boot, JPA y PostgreSQL**.

---

# 🚀 Funcionalidades

La aplicación permite realizar las siguientes operaciones desde un menú interactivo en consola:

1. 🔎 **Buscar libro por título**
   Consulta la API de Gutendex y guarda el libro en la base de datos.

2. 📖 **Listar libros registrados**
   Muestra todos los libros almacenados en la base de datos.

3. 👨‍💻 **Listar autores registrados**
   Muestra los autores almacenados.

4. 🕰 **Listar autores vivos en un determinado año**
   Permite consultar qué autores estaban vivos en un año específico.

5. 🌎 **Listar libros por idioma**
   Permite filtrar libros según su idioma.

6. 📊 **Top 10 libros más descargados**
   Muestra los libros con mayor número de descargas.

---

# 🧰 Tecnologías utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* **Jackson (JSON parsing)**
* **HTTP Client de Java**
* **API Gutendex**

---

# 🌐 API utilizada

Este proyecto consume la API pública:

**Gutendex**

https://gutendex.com/

Ejemplo de consulta:

https://gutendex.com/books/?search=don+quijote

Esta API proporciona información como:

* Título del libro
* Autor
* Idioma
* Número de descargas
* Año de nacimiento y muerte del autor

---

# 📂 Estructura del proyecto

```
src
 └─ main
     ├─ java
     │   └─ com.literalura
     │        ├─ principal
     │        │     Principal.java
     │        │
     │        ├─ model
     │        │     Autor.java
     │        │     Libros.java
     │        │
     │        ├─ dto
     │        │     Datos.java
     │        │     DatosLibro.java
     │        │     DatosAutor.java
     │        │
     │        ├─ repository
     │        │     AutorRepository.java
     │        │     LibroRepository.java
     │        │
     │        └─ service
     │              ConsumoAPI.java
     │              ConvierteDatos.java
     │
     └─ resources
           application.properties
```

---

# ⚙️ Instalación y ejecución

## 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/literalura.git
cd literalura
```

---

## 2️⃣ Configurar la base de datos

Este proyecto utiliza **PostgreSQL**.

Crear una base de datos:

```sql
CREATE DATABASE literalura;
```

Configurar el archivo:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
```

---

## 3️⃣ Ejecutar la aplicación

Puedes ejecutarla desde:

### IntelliJ / Eclipse

Ejecutando la clase principal del proyecto.

### O desde terminal:

```bash
mvn spring-boot:run
```

---

# 📋 Ejemplo de uso

Menú principal:

```
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
6 - Top 10 libros más descargados
0 - Salir
```

Ejemplo de salida:

```
----- LIBRO -----
Título: Don Quijote
Autor: Miguel de Cervantes
Idioma: Español
Descargas: 13446
-----------------
```

---

# 🧠 Conceptos aplicados en el proyecto

Este proyecto aplica varios conceptos importantes del desarrollo backend:

* Consumo de APIs REST
* Deserialización de JSON
* Uso de **Records en Java**
* Persistencia con **Spring Data JPA**
* Relaciones entre entidades
* Consultas personalizadas con JPQL
* Manejo de excepciones
* Aplicaciones de consola interactivas

---

# 👨‍💻 Autor

Proyecto desarrollado por:

**Marco Antonio Romero Andrade**

Desarrollador backend enfocado en:

* Java
* Spring Boot
* SQL
* APIs REST
