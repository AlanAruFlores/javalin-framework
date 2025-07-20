package com.ar.javalin.base.models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Builder
public class Item {
    private Long id;
    private String description;    
}
