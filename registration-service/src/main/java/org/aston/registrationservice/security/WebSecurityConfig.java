package org.aston.registrationservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

        @SuppressWarnings({"deprecation", "unused"})
        @Bean
        public static NoOpPasswordEncoder passwordEncoder() {
            return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        }

        @SuppressWarnings({"unused"})
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/", "/home", "/static/**", "/WEB-INF/templates/**").permitAll()
//                        .requestMatchers("/login", "/info", "/registration").anonymous()
//                        .requestMatchers("/logout").authenticated()
//                        .requestMatchers("/add**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .logout(Customizer.withDefaults());

                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/","/static/**", "/pizzeria","/login", "/info", "/img/**", "/css/**", "/registration").permitAll()
                            // .requestMatchers("/adminpage/**").hasRole(Role.ADMIN.name())
                            .requestMatchers("/orderpage").authenticated()
                            // .requestMatchers(antMatcher("/client/{\\d}/delete")).hasAnyAuthority(Role.ADMIN.getAuthority(), Role.USER.getAuthority())
                            .requestMatchers( "/api/**").anonymous()
                            .anyRequest().authenticated())
                    .formLogin(login -> login
                            .loginPage("/login")
                            .defaultSuccessUrl("/orderpage")
                            .permitAll())
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .deleteCookies("JSESSIONID"));
            return http.build();
        }

//        @SuppressWarnings({"unused"})
//        @Bean
//        public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
//            return new HandlerMappingIntrospector();
//        }

    }
