package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserServiceImpl userDetailsService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler,
                             @Lazy UserServiceImpl userDetailsService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/profile/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .loginPage("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http
//            // Временно отключаем CSRF и включаем CORS
//            .csrf().disable()
//            .cors().disable()
//
//            // Настраиваем правила доступа
//            .authorizeRequests()
//            .antMatchers("/api/**").permitAll() // ДОЛЖНО БЫТЬ ПЕРВЫМ!
//            .antMatchers("/login", "/error").permitAll()
//            .antMatchers("/profile/**").hasAnyRole("USER", "ADMIN")
//            .antMatchers("/admin/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//
//            .and()
//            // Настраиваем форму логина
//            .formLogin()
//            .successHandler(successUserHandler)
//            .loginPage("/")
//            .usernameParameter("email")
//            .passwordParameter("password")
//            .permitAll()
//
//            .and()
//            // Настраиваем логаут
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/")
//            .permitAll()
//
//            .and()
//            // Включаем Basic Auth
//            .httpBasic();
//}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
}