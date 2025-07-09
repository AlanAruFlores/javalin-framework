package com.ar.javalin.base.dto.response;

import lombok.Builder;

@Builder
public class ErrorResponseDto {
    public int code;
    public String message;
}
