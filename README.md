# Aplicación Web con Javalin 6

Este proyecto es una aplicación web desarrollada con [Javalin 6](https://javalin.io/), un framework ligero y moderno para Java y Kotlin, con soporte completo para OpenAPI y Swagger.

## ¿Qué es Javalin?

Javalin es un framework web que destaca por su simplicidad, rapidez y facilidad de uso. Permite crear aplicaciones web y APIs REST de manera intuitiva, ofreciendo características como:

- Alto rendimiento y bajo consumo de recursos
- API clara y fácil de aprender
- Basado en Jetty
- Compatible tanto con Java como con Kotlin
- Soporte integrado para WebSocket y JSON
- Sistema de rutas sencillo y flexible
- Fácil integración con otras bibliotecas y herramientas
- Soporte nativo para OpenAPI

## Características del Proyecto

Esta aplicación incluye:

- **Javalin 6.6.0** con Jetty embebido
- **OpenAPI 3.0** para documentación automática de APIs
- **Swagger UI** para visualización interactiva de la documentación
- **Javalin MVC** para arquitectura basada en controladores
- **Lombok** para reducir código boilerplate
- **Jackson** para serialización JSON
- **Dagger** para inyección de dependencias

## Endpoints Disponibles

- `GET /index`: Endpoint de ejemplo que devuelve un item JSON
- `GET /openapi`: Especificación OpenAPI en formato JSON
- `GET /swagger`: Interfaz Swagger UI para explorar la API
- `GET /index.html`: Archivo estático desde el directorio `public`

## Manejo de respuestas de error como JSON en Javalin

Para devolver respuestas de error (o cualquier objeto) como JSON en Javalin, utiliza el método `ctx.json()`. Este método serializa automáticamente tu objeto (por ejemplo, un DTO) a una cadena JSON y establece el encabezado `Content-Type` correctamente. Ejemplo:

```java
ctx.status(404).json(
    ErrorResponseDto.builder()
        .code(404)
        .message(exception.getMessage())
        .build()
);
```

**Nota:**
- `ctx.result()` solo acepta un argumento de tipo `String`. Si pasas un objeto (como un DTO), obtendrás un error de compilación. Si necesitas serializar manualmente un objeto, utiliza una librería JSON (por ejemplo, Jackson) para convertirlo a cadena y luego pásalo a `ctx.result()`. Sin embargo, la forma recomendada y más sencilla en Javalin es usar `ctx.json()`.

## Primeros Pasos

### Para ejecutar la aplicación:

1. Asegúrate de tener Java 17+ instalado en tu sistema
2. Navega al directorio del proyecto:
   ```bash
   cd first-project
   ```
3. Compila el proyecto:
   ```bash
   mvn clean compile
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn exec:java -Dexec.mainClass="com.ar.javalin.base.App"
   ```
5. Accede a la aplicación:
   - API: `http://localhost:8080/index`
   - Documentación OpenAPI: `http://localhost:8080/openapi`
   - Swagger UI: `http://localhost:8080/swagger`
   - Archivos estáticos: `http://localhost:8080/index.html`

## Dependencias Principales

```xml
<!-- Javalin Bundle (incluye Javalin, Jackson, Logger y Jetty embebido) -->
<dependency>
    <groupId>io.javalin</groupId>
    <artifactId>javalin-bundle</artifactId>
    <version>6.6.0</version>
</dependency>

<!-- OpenAPI Plugin -->
<dependency>
    <groupId>io.javalin.community.openapi</groupId>
    <artifactId>javalin-openapi-plugin</artifactId>
    <version>6.6.0</version>
</dependency>

<!-- Swagger UI Plugin -->
<dependency>
    <groupId>io.javalin.community.openapi</groupId>
    <artifactId>javalin-swagger-plugin</artifactId>
    <version>6.6.0</version>
</dependency>

<!-- Javalin MVC -->
<dependency>
    <groupId>com.truncon</groupId>
    <artifactId>javalin-mvc-api</artifactId>
    <version>5.0.1</version>
</dependency>
<dependency>
    <groupId>com.truncon</groupId>
    <artifactId>javalin-mvc-core</artifactId>
    <version>5.0.1</version>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.38</version>
</dependency>

<!-- Dagger para inyección de dependencias -->
<dependency>
    <groupId>com.google.dagger</groupId>
    <artifactId>dagger</artifactId>
    <version>2.44</version>
</dependency>
```

## Configuración de Anotaciones

El proyecto utiliza procesadores de anotaciones para generar código automáticamente:

```xml
<annotationProcessorPaths>
    <!-- Javalin MVC Generator -->
    <path>
        <groupId>com.truncon</groupId>
        <artifactId>javalin-mvc-generator</artifactId>
        <version>5.0.1</version>
    </path>
    <!-- OpenAPI Annotation Processor -->
    <path>
        <groupId>io.javalin.community.openapi</groupId>
        <artifactId>openapi-annotation-processor</artifactId>
        <version>6.6.0</version>
    </path>
</annotationProcessorPaths>
```
