package com.ar.javalin.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ar.javalin.base.exceptions.api.ExceptionHandlerContext;
import com.ar.javalin.base.utils.PathUtilsConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.truncon.javalin.mvc.ControllerRegistry;
import com.truncon.javalin.mvc.api.Injector;
import com.truncon.javalin.mvc.JavalinControllerRegistry;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JavalinJackson;
import io.javalin.json.JsonMapper;
import io.javalin.openapi.OpenApiInfo;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

public final class JavalinFactory {
    private static final Logger LOGGER;
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        LOGGER = LoggerFactory.getLogger(JavalinFactory.class.getName());
        OBJECT_MAPPER = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    }

    private final Provider<Injector> injectorProvider;
    private ExceptionHandlerContext exceptionHandlerContext;

    @Inject
    public JavalinFactory(Provider<Injector> injectorProvider){
        this.injectorProvider = injectorProvider;
        exceptionHandlerContext = injectorProvider.get().getInstance(ExceptionHandlerContext.class);
    }


    public Javalin create(){   
        Javalin app = Javalin.create(config -> {
            // Configure OpenAPI
            OpenApiConfiguration openApiConfig = getOpenApiOptions();
            config.plugins.register(new OpenApiPlugin(openApiConfig));
            
            // Configure Swagger
            SwaggerConfiguration swaggerConfig = new SwaggerConfiguration();
            config.plugins.register(new SwaggerPlugin(swaggerConfig));
            
            JsonMapper jsonMapper = new JavalinJackson(OBJECT_MAPPER);
            config.jsonMapper(jsonMapper);
            
            // Re-enabling static files from the 'public' directory
            config.staticFiles.add(PathUtilsConstants.WEB_RESOURCE_PATH, Location.EXTERNAL);
        });

        // Javalin MVC generates "com.truncon.javalin.mvc.JavalinControllerRegistry" automatically at compile time
        ControllerRegistry registry = new JavalinControllerRegistry(injectorProvider::get);
        registry.register(app);

        // Prevent unhandled exceptions from taking down the web server
        app.exception(Exception.class, (e, ctx) -> exceptionHandlerContext.handle(e, ctx));

        app.wsException(
            Exception.class,
            (e, ctx) -> LOGGER.error("Encountered an unhandled exception for WebSockets.", e));

        return app;
    }

    private static OpenApiConfiguration getOpenApiOptions() {
        OpenApiConfiguration configuration = new OpenApiConfiguration();
        OpenApiInfo info = configuration.getInfo();
        info.setTitle("Api Javalin application");
        info.setVersion("1.0");
        return configuration;
    }
}
