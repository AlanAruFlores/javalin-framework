# Aplicación Web con Javalin

Este proyecto es una aplicación web sencilla desarrollada con [Javalin](https://javalin.io/), un framework ligero y moderno para Java y Kotlin.

## ¿Qué es Javalin?

Javalin es un framework web que destaca por su simplicidad, rapidez y facilidad de uso. Permite crear aplicaciones web y APIs REST de manera intuitiva, ofreciendo características como:

- Alto rendimiento y bajo consumo de recursos
- API clara y fácil de aprender
- Basado en Jetty
- Compatible tanto con Java como con Kotlin
- Soporte integrado para WebSocket y JSON
- Sistema de rutas sencillo y flexible
- Fácil integración con otras bibliotecas y herramientas

## Primeros Pasos

Esta aplicación inicia un servidor web en el puerto 8080 y expone un endpoint básico:

- `GET /`: Devuelve "Hello World"

### Para ejecutar la aplicación:

1. Asegúrate de tener Java instalado en tu sistema.
2. Compila el proyecto utilizando Maven o Gradle.
3. Ejecuta la clase principal (`App` o `Main`).
4. Accede a `http://localhost:8080` desde tu navegador.

## Dependencias

El proyecto utiliza Javalin como dependencia principal. Asegúrate de incluirlo en tu archivo de construcción (`pom.xml` o `build.gradle`).
 