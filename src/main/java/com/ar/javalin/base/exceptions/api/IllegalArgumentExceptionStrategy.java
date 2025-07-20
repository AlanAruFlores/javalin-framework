package com.ar.javalin.base.exceptions.api;

import com.ar.javalin.base.dto.response.ErrorResponseDto;

import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IllegalArgumentExceptionStrategy implements ExceptionHandlerStrategy<IllegalArgumentException>{

    @Override
    public void handle(IllegalArgumentException exception, Context ctx) {
        log.error("IllegalArgumentException occurred: " + exception.getMessage());

        ctx.status(400).json(
            ErrorResponseDto.builder()
            .code(400)
            .message(exception.getMessage())
            .build()
        );
    }
    
}