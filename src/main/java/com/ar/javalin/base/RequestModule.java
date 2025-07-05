package com.ar.javalin.base;

import com.ar.javalin.base.controller.HomeController;
import com.ar.javalin.base.controller.ItemController;
import com.google.inject.AbstractModule;
import com.truncon.javalin.mvc.api.MvcModule;

@MvcModule
public final class RequestModule extends AbstractModule{
    
    @Override
    protected void configure(){
        super.configure(); 
        
        // Bind controllers to the Guice injector
        bind(HomeController.class);
        bind(ItemController.class);
    }
}
