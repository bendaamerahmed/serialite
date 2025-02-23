package com.serialite.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for SeriaLite integration.
 */
@ConfigurationProperties(prefix = "seria-lite")
public class SeriaLiteProperties {

    /**
     * Default serialization format to use if none is specified.
     */
    private String defaultFormat = "json";

    public String getDefaultFormat() {
        return defaultFormat;
    }

    public void setDefaultFormat(String defaultFormat) {
        this.defaultFormat = defaultFormat;
    }
}