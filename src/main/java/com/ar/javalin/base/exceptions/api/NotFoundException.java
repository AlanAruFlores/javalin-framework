package com.ar.javalin.base.exceptions.api;

import com.ar.javalin.base.dto.response.ErrorResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException implements ExceptionHandlerStrategy<IllegalStateException> {

    @Override
    public ErrorResponseDto handle(IllegalStateException exception) {
        // Log the exception or perform any other necessary actions
        log.error("NotFoundException occurred: " + exception.getMessage());
        

        return ErrorResponseDto.builder()
                .code(404)
                .message("Resource not found")
                .build();
    }
}
