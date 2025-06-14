package com.ar.javalin.base;

import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

public class Main {

    static final int PORT = 8080;
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new OpenApiPlugin(pluginConfig -> {
                pluginConfig.withDefinitionConfiguration((version, definition) -> {
                    definition.withInfo(info -> {
                        info.setTitle("Javalin OpenAPI example");
                    });
                });
            }));
            config.registerPlugin(new SwaggerPlugin());
            config.registerPlugin(new ReDocPlugin());
        });

        app.start(PORT);
    }
}