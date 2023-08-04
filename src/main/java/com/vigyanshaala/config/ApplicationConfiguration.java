//package com.vigyanshaala.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//
//public class ApplicationConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//
////        return http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/token/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
////                .httpBasic(Customizer.withDefaults())
////                .build();
//        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/job/entitlement/*").authenticated().anyRequest().permitAll()).oauth2Login(withDefaults());
//        return http.build();
//    }
//}
