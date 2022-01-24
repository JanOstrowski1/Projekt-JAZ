package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("1234")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("password")
                .roles("ADMIN");

    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/quotes/add").hasAnyRole("USER", "ADMIN")
                .antMatchers("/quotes/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/quotes/delete").hasAnyRole("USER", "ADMIN")
                .antMatchers("/quotes/update").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/").permitAll()
                .and()
                .formLogin();
//                .loginPage("/login")
//                .permitAll()

    }

}
