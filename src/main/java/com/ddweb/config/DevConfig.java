package com.ddweb.config;

import com.ddweb.annotations.DevProfile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@DevProfile
@Configuration
@PropertySource("classpath:application-dev.properties")
public class DevConfig {
}
