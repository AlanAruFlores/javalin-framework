package com.ar.javalin.base.controller;
import io.javalin.http.Context;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;

public class MyController {
    @OpenApi(
        path = "/hello",
        methods = {HttpMethod.GET},
        summary = "Say hello",
        responses = {
            @OpenApiResponse(status = "200", content = @OpenApiContent(from = String.class))
        }  
    )
    public void getHello(Context ctx) {
        ctx.result("Hello, world!");
    }
}
