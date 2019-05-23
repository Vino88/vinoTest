package com.vino.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /*@Bean
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/auth/**", "/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureHandler(eventAuthenticationFailureHandler)
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/auth/login?error=true")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .rememberMe()
                .key("s3cr3tKEY")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/auth/login")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/js/**", "/images/**", "/node_modules/**", "/fonts/**","/css/**", "/fingerprint/**");
    }
}
