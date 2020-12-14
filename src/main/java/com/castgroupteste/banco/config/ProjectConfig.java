package com.castgroupteste.banco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true, // @RolesAllowed
        prePostEnabled = true
)
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails u1 = User.withUsername("romano")
                .password("12345")
                .roles("ADMIN")
                .build();
        UserDetails u2 = User.withUsername("raphael")
                .password("12345")
                .roles("USER")
                .build();
        manager.createUser(u1);
        manager.createUser(u2);

        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .headers().frameOptions().disable();
    } 
}
