package com.ar.javalin.base.settings;

public class AppSettings implements ApplicationSettings{
    private final int PORT = 8080;
    
    @Override
    public int getPort() {
        return PORT;
    }


}
