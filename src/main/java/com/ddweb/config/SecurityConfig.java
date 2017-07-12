package com.ddweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/api").permitAll()
                .antMatchers("/auth/islogged/**").authenticated()
                .and()
                .formLogin()
        .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/logoutinfo")
                .permitAll()
                .and().csrf().disable();
    }

}