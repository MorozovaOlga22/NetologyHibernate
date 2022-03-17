package com.example.hibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("adminIvanov").password("{noop}AdminPassword").roles("admin")
                .and()
                .withUser("justUserPetrov").password("{noop}123").roles();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String[] simpleSearchQueries = {"/persons/by-city", "/persons/by-age", "/persons/by-name-surname"};
        final String[] extendedSearchQueries = {"/persons/find-by-id", "/persons/exists-by-id", "/persons/find-all", "/persons/find-all-by-id", "/persons/count"};
        http.formLogin().and()
                .authorizeHttpRequests().antMatchers(simpleSearchQueries).permitAll()
                .and()
                .authorizeHttpRequests().antMatchers(extendedSearchQueries).authenticated()
                .and()
                .authorizeHttpRequests().anyRequest().hasRole("admin");
    }
}