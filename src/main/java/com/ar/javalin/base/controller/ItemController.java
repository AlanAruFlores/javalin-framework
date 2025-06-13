package com.ar.javalin.base.controller;

import com.truncon.javalin.mvc.api.ActionResult;
import com.truncon.javalin.mvc.api.Controller;
import com.truncon.javalin.mvc.api.JsonResult;

@Controller
public final class ItemController {
    
    public ActionResult index(){
        return JsonResult(new IndexModel());
    }
}
