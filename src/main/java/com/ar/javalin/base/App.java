package com.ar.javalin.base;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.json.JavalinJackson;
import io.javalin.json.JsonMapper;
import com.truncon.javalin.mvc.ControllerRegistry;
import com.truncon.javalin.mvc.JavalinControllerRegistry;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.openapi.OpenApiInfo;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import lombok.extern.slf4j.Slf4j;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;

@Slf4j
public final class App {
    public static void main(String[] args) throws IOException {
        Javalin app = Javalin.create(config -> {
            // Configure OpenAPI
            OpenApiConfiguration openApiConfig = getOpenApiOptions();
            config.plugins.register(new OpenApiPlugin(openApiConfig));
            
            // Configure Swagger
            SwaggerConfiguration swaggerConfig = new SwaggerConfiguration();
            swaggerConfig.setDocumentationPath("/swagger"); // Set Swagger UI path
            config.plugins.register(new SwaggerPlugin(swaggerConfig));
            
            // Register you JSON mapper with whatever library you want
            ObjectMapper objectMapper = new ObjectMapper(); // customize as needed
            JsonMapper jsonMapper = new JavalinJackson(objectMapper);
            config.jsonMapper(jsonMapper);
            
            // This example is using the SPA feature with static files
          //  config.staticFiles.add("./public", Location.EXTERNAL);
          //  config.staticFiles.add("/", Location.EXTERNAL);
        });

        // Javalin MVC generates "com.truncon.javalin.mvc.JavalinControllerRegistry" automatically at compile time
        ControllerRegistry registry = new JavalinControllerRegistry();
        registry.register(app);

        // Prevent unhandled exceptions from taking down the web server
        app.exception(Exception.class, (e, ctx) -> {
            log.error("Encountered an unhandled exception.", e);
            ctx.status(500);
        });

        app.start(8080);
    }

    private static OpenApiConfiguration getOpenApiOptions() {
        OpenApiConfiguration configuration = new OpenApiConfiguration();
        configuration.setDocumentationPath("/openapi");
        OpenApiInfo info = configuration.getInfo();
        info.setTitle("My API");
        info.setVersion("1.0");
        info.setVersion("3.0.0");
        return configuration;
    }
}