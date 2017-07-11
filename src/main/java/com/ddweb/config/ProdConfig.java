package com.ddweb.config;

import com.ddweb.annotations.ProdProfile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ProdProfile
@Configuration
@PropertySource("classpath:application-prod.properties")
public class ProdConfig {
}
