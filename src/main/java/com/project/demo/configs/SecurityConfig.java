package com.project.demo.configs;

import com.project.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/**").hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                    .formLogin().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    //        auth.inMemoryAuthentication()
    //                .withUser("user").password("{noop}password").roles("USER")
    //                .and()
    //                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    //    }

    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //        auth.jdbcAuthentication()
    //                .dataSource(dataSource)
    //                .passwordEncoder(NoOpPasswordEncoder.getInstance())
    //                .usersByUsernameQuery("select email, password from users where email=?")
    //                .authoritiesByUsernameQuery("select u.email, u.password from users u inner join user_role ur on u.id=ur.user_id, where u.email=?");
    //    }
}
