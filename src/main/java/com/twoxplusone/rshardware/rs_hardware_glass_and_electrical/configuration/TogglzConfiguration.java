package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.configuration;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.liveconfig.Payment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.activation.DefaultActivationStrategyProvider;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

@Configuration
public class TogglzConfiguration {
    @Bean
    public FeatureProvider featureProvider(){
        return new EnumBasedFeatureProvider(Payment.class);
    }
    @Bean
    public StateRepository stateRepository() {
        return new InMemoryStateRepository();
    }

    @Bean
    public UserProvider userProvider() {
        return new NoOpUserProvider();
    }

    @Bean
    public FeatureManager featureManager(StateRepository stateRepository) {
        return new FeatureManagerBuilder()
                .featureEnum(Payment.class)
                .stateRepository(stateRepository)
                .userProvider(new NoOpUserProvider())
                .build();
    }
}
