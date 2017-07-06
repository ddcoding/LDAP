package com.ddweb;

import com.ddweb.config.LuaConfig;
import com.ddweb.service.LdapConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

	@SpringBootApplication
    public class LdapApplication {

        public static void main(String[] args) {
            ConfigurableApplicationContext ctx = SpringApplication.run(LdapApplication.class,args);
            LdapConnection ldapConnection = ctx.getBean(LdapConnection.class);
            LuaConfig luaConfig = ctx.getBean(LuaConfig.class);
            ldapConnection.ConnectViaLdap();
            luaConfig.LuaMainInit();
        }
    }
