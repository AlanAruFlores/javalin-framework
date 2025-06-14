package com.ar.javalin.base.controller;

import com.ar.javalin.base.models.Item;
import com.truncon.javalin.mvc.api.ActionResult;
import com.truncon.javalin.mvc.api.Controller;
import com.truncon.javalin.mvc.api.HttpGet;
import com.truncon.javalin.mvc.api.JsonResult;

@Controller
public final class ItemController {
    
    @HttpGet(route = "/index")
    public ActionResult index(){
        return new JsonResult(Item.builder()
                .id(1L)
                .description("This is a sample item.")
                .build());
    }
}
