package com.luca.AutogarageGianlucaMeens;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    //
    //user roles met de bijbehoorende gebruikersnamen en wachtwoorden
    //
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Monteur").password("{noop}monteur123").roles("MONTEUR", "MEDEWERKER")
                .and()
                .withUser("backoffice").password("{noop}backoffice123").roles("BACKOFFICE", "MEDEWERKER")
                .and()
                .withUser("Admin").password("{noop}admin123").roles("ADMIN", "MEDEWERKER")
                .and()
                .withUser("super").password("{noop}super123").roles("MONTEUR", "BACKOFFICE", "ADMIN", "MEDEWERKER")
        ;
    }


    //
    // toegang tot paths wordt gekoppeld aan een role
    //
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
                .antMatchers("/klanten/**").hasAnyRole("MONTEUR","ADMIN")
                .antMatchers("/Autos/**").hasAnyRole("MONTEUR", "ADMIN")
                .antMatchers("/gebruikteOnderdelen/**").hasAnyRole("MONTEUR", "ADMIN")
                .antMatchers("/Onderdelen/**").hasRole( "BACKOFFICE")
                .antMatchers("/reparaties/**").hasAnyRole("MONTEUR")
                .antMatchers("/Keuringen/**").hasAnyRole("MONTEUR", "ADMIN")
                .antMatchers("/AutoPapieren/**").hasAnyRole( "ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}