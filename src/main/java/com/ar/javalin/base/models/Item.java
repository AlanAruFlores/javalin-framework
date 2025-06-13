package com.ar.javalin.base.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class Item {
    private Long id;
    private String description;    
}
