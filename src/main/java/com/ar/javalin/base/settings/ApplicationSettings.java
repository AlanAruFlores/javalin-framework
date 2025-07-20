package com.ar.javalin.base.settings;

public interface ApplicationSettings {
    int getPort();
    String getHtmlResourcePath();
    String getDbUrl();
    String getDbUser();
    String getDbPassword();
    String getDbDriver();
}
