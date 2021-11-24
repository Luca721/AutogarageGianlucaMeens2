package com.luca.AutogarageGianlucaMeens;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Monteur").password("{noop}peer").roles("MONTEUR", "MEDEWERKER")
                .and()
                .withUser("backoffice").password("{noop}appel").roles("BACKOFFICE", "MEDEWERKER")
                .and()
                .withUser("Admin").password("{noop}bosbes").roles("ADMIN", "MEDEWERKER")
                .and()
                .withUser("super").password("{noop}kiwi").roles("MONTEUR", "BACKOFFICE", "ADMIN", "MEDEWERKER")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .httpBasic()
                .and()
                .formLogin()
                .defaultSuccessUrl("/home")
                .and()
                .authorizeRequests()
                .antMatchers("/autopapieren/**").hasRole("ADMIN")
                .antMatchers("/klanten/**").hasAnyRole("ADMIN")
                .antMatchers("/Autos/**").hasAnyRole("MONTEUR", "ADMIN")
                .antMatchers("/gebruikteOnderdelen/**").hasAnyRole("MONTEUR", "ADMIN")
                .antMatchers("/Onderdelen/**").hasRole( "ADMIN")
                .antMatchers("/reparaties/**").hasAnyRole("MONTEUR")
                .antMatchers("/Keuringen/**").hasAnyRole("MONTEUR")
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}
