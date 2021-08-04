package com.lance.config;

import com.lance.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 设置审计
 * @author lancer1126
 */
@Component("auditorAware")
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.of(SecurityUtils.getCurrentUsername());
        } catch (Exception ignored) {}
        return Optional.of("System");
    }
}
