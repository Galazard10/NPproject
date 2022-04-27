//package com.project.demo.configs;
//
//import com.project.demo.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling().accessDeniedPage("/accessdeniedpage");
//        http.authorizeRequests().antMatchers("/").permitAll();
//        http.formLogin().
//                loginPage("/loginpage").permitAll().
//                loginProcessingUrl("/authpage").permitAll().
//                usernameParameter("user_email").
//                passwordParameter("user_password").
//                defaultSuccessUrl("/profilepage").
//                failureUrl("/loginpage?error");
//        http.logout().permitAll().
//                logoutUrl("/tologout").
//                logoutSuccessUrl("/loginpage");
//
//        http.csrf().disable();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
