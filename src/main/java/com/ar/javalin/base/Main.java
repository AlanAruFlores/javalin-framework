package com.ar.javalin.base;

import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.register(new OpenApiPlugin(openApiConfiguration-()));
            config.plugins.register(new SwaggerPlugin(swaggerConfiguration()));
            config.plugins.register(new ReDocPlugin(reDocConfiguration()));
        })
        .get("/", ctx -> ctx.result("Hello World"))
        .start(8080);
    }

    private static OpenApiConfiguration openApiConfiguration() {
        return new OpenApiConfiguration()
                .withInfo(info -> info
                    .title("My API")
                    .version("1.0.0")
                    .description("This is a sample API."));
    }

    private static SwaggerConfiguration swaggerConfiguration() {
        return new SwaggerConfiguration();
    }

    private static ReDocConfiguration reDocConfiguration() {
        return new ReDocConfiguration();
    }
}