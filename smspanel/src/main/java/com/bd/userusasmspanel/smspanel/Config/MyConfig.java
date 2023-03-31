package com.bd.userusasmspanel.smspanel.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
    @Bean
    public UserDetailsService getUserDetailService() {
        return new LoginDetilsService();
    }
    @Bean
    public BCryptPasswordEncoder PasswordEncoder(){
       return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http.authorizeRequests().antMatchers("/admin/**")
             .hasRole("ADMIN")
             .antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll()
             .and().formLogin().loginPage("/login").
             loginProcessingUrl("/do_login")
             .defaultSuccessUrl("/user/index").
             and().csrf().disable();

    }
}
