package com.ar.javalin.base.exceptions.api;

import com.ar.javalin.base.dto.response.ErrorResponseDto;
import com.ar.javalin.base.exceptions.api.exceptions.InternalServerException;

import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerExceptionStrategy implements ExceptionHandlerStrategy<InternalServerException> {

    @Override
    public void handle(InternalServerException exception, Context ctx) {
        log.error("InternalServerException occurred: " + exception.getMessage());

        ctx.status(500).json(
            ErrorResponseDto.builder()
            .code(500)
            .message(exception.getMessage())
            .build()
        );
    }
    
}
