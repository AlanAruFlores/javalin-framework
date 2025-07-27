package com.ar.javalin.base.settings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.OptionalInt;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;

public class AppSettings implements ApplicationSettings{

    private static final String APPLICATION_CONFIG_PATH = "./src/main/resources/application.conf";

    private final ConfigWrapper configWrapper;   
    private final int PORT_DEFAULT = 8080;
    private final int H2_PORT_DEFAULT = 8091;



    public AppSettings(ConfigWrapper wrapper){
        this.configWrapper = wrapper;        
    }


    public static AppSettings newInstance() {
        Path configurationPath = Paths.get(APPLICATION_CONFIG_PATH);
        Config config = ConfigFactory.parseFile(configurationPath.toFile());
        ConfigWrapper wrapper = new ConfigWrapper(config);
        return new AppSettings(wrapper);
    }

    @Override
    public int getPort() {
        return getWebServerConfig().getInteger("port").orElse(PORT_DEFAULT);
    }

    @Override
    public int getH2Port(){
        return getWebServerConfig().getInteger("h2-port").orElse(H2_PORT_DEFAULT);
    }

    @Override 
    public String getHtmlResourcePath(){
        return getWebResourcesConfig().getString("html")
            .orElseThrow(() -> new IllegalStateException("Resource path is not configured in application.conf"));
    }

    public ConfigWrapper getWebServerConfig(){
        return this.configWrapper.atPath("web.server");
    }

    public ConfigWrapper getWebResourcesConfig(){
        return this.configWrapper.atPath("web.resources");
    }

    private static final class ConfigWrapper {
        private final Config config;

        public ConfigWrapper(Config config) {
            this.config = config;
        }

        public ConfigWrapper atPath(String path) {
            if (this.config == null || !this.config.hasPath(path)) {
                return new ConfigWrapper(null);
            }
            Config childConfig = this.config.getConfig(path);
            return new ConfigWrapper(childConfig);
        }

        public Optional<String> getString(String path) {
            if (this.config == null || !this.config.hasPath(path)) {
                return Optional.empty();
            }
            ConfigValue value = this.config.getValue(path);
            return Optional.of(value.unwrapped().toString());
        }

        public Optional<Boolean> getBoolean(String path) {
            if (this.config == null || !this.config.hasPath(path)) {
                return Optional.empty();
            }
            ConfigValue value = this.config.getValue(path);
            if (value.valueType() == ConfigValueType.BOOLEAN) {
                return Optional.of((Boolean) value.unwrapped());
            }
            return Optional.empty();
        }

        public OptionalInt getInteger(String path) {
            return getString(path)
                .stream()
                .flatMapToInt(s -> toInteger(s).stream())
                .findFirst();
        }

        private static OptionalInt toInteger(String value) {
            if (value == null || value.isBlank()) {
                return OptionalInt.empty();
            }
            try {
                int intValue = Integer.parseInt(value);
                return OptionalInt.of(intValue);
            } catch (NumberFormatException exception) {
                return OptionalInt.empty();
            }
        }
    }
}
