package com.ddweb;

import com.ddweb.config.LuaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
public class LdapApplication {
    private static final Logger log = LoggerFactory.getLogger(LdapApplication.class);


    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext ctx = SpringApplication.run(LdapApplication.class, args);
        LuaConfig luaConfig = ctx.getBean(LuaConfig.class);
        luaConfig.LuaMainInit();
        Environment env = ctx.getEnvironment();
        String protocol = "http";
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
