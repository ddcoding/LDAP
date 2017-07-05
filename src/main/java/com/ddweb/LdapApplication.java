package com.ddweb;

import com.ddweb.ldap.LdapConnection;
import com.ddweb.lua.LuaConnect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

	@SpringBootApplication
    public class LdapApplication {

        public static void main(String[] args) {
            ConfigurableApplicationContext ctx = SpringApplication.run(LdapApplication.class,args);
            LdapConnection ldapConnection = ctx.getBean(LdapConnection.class);
            LuaConnect luaConnect = ctx.getBean(LuaConnect.class);
            ldapConnection.ConnectViaLdap();
            luaConnect.ExamplePrint();
        }
    }
