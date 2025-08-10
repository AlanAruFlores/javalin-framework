package com.ar.javalin.base.configuration;
import org.h2.tools.Server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.sql.SQLException;

@Slf4j
@Getter
public class H2ConsoleConfiguration {
    private Server h2Server;

    public void startH2Console(String port) {
        try {
            String persistenceUnitName = System.getenv("PROFILE");

            if(!persistenceUnitName.equalsIgnoreCase("dev")) {
                log.info("H2 Console is only available in development mode. Current mode: {}", persistenceUnitName);
                return;
            }

            h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", port).start();
            log.info("H2 Console started at http://localhost:9091");
        } catch (SQLException e) {
            log.error("Failed to start H2 Console", e);
        }
    }
    
    
}
