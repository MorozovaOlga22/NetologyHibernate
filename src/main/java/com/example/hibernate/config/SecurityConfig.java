package com.example.hibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("adminIvanov").password("{noop}AdminPassword").roles("admin")
                .and().withUser("justUserPetrov").password("{noop}123").roles()
                .and().withUser("userRead").password("{noop}1").roles("READ", "admin")
                .and().withUser("userWrite").password("{noop}2").roles("WRITE", "admin")
                .and().withUser("userDelete").password("{noop}3").roles("DELETE", "admin")
                .and().withUser("userMultiRoles").password("{noop}4").roles("READ", "WRITE", "DELETE", "admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String[] simpleSearchQueries = {"/persons/by-city", "/persons/by-age", "/persons/by-name-surname"};
        final String[] extendedSearchQueries = {"/persons/find-by-id", "/persons/exists-by-id", "/persons/find-all", "/persons/find-all-by-id", "/persons/count"};
        http.formLogin().and()
                .authorizeHttpRequests().antMatchers(simpleSearchQueries).permitAll()
                .antMatchers(extendedSearchQueries).authenticated()
                .anyRequest().hasRole("admin");
    }
}