package com.ddweb.config;

import com.ddweb.annotations.ProdProfile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *  Annotation used to set element visible only on production profile
 */
@ProdProfile
@Configuration
@PropertySource("classpath:application-prod.properties")
public class ProdConfig {
}
