package com.serialite.spring;

import com.serialite.core.SeriaLite;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration for SeriaLite in a Spring Boot environment.
 */
@Configuration
@ConditionalOnClass(SeriaLite.class)
public class SeriaLiteAutoConfiguration {

    /**
     * Exposes SeriaLite as a bean in the Spring context if none is already defined.
     *
     * @return SeriaLite instance
     */
    @Bean
    @ConditionalOnMissingBean
    public SeriaLite seriaLite() {
        return new SeriaLite();
    }
}