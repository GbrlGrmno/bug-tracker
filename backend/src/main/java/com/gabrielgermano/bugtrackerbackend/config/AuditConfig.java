package com.gabrielgermano.bugtrackerbackend.config;

import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.security.impl.AuditorAwareImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@RequiredArgsConstructor
@Configuration
public class AuditConfig {

    private final AuditorAwareImpl auditorAware;

    @Bean
    public AuditorAware<Long> auditorAware() {
        return auditorAware;
    }
}
