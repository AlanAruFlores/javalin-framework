package com.ar.javalin.base.exceptions.api;

import com.ar.javalin.base.dto.response.ErrorResponseDto;

public interface ExceptionHandlerStrategy <T extends Exception>{
    ErrorResponseDto handle(T exception);
}
