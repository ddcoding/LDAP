package com.ddweb.config;

import com.ddweb.annotations.DevProfile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 *  Annotation used to set element visible only on development profile
 */
@DevProfile
@Configuration
@PropertySource("classpath:application-dev.properties")
public class DevConfig {
}
