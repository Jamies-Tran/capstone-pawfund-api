//package com.paw.fund.configuration.secure;
//
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//@SuppressWarnings("deprecate")
//public class SecurityConfiguration {
//
//    PawFundSecurityFilter securityFilter;
//
//
//    PawFundUserDetailService userDetailService;
//
//
//    PasswordEncoder passwordEncoder;
//
//
//    PawFundAuthenticationEntryPoint authenticationEntryPoint;
//
//    private CorsConfigurationSource customCorsConfiguration() {
//        CorsConfiguration cors = new CorsConfiguration();
//        cors.addAllowedHeader("*");
//        cors.addAllowedMethod("*");
//        cors.addAllowedOrigin("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", cors);
//
//        return source;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailService);
//        authProvider.setPasswordEncoder(passwordEncoder);
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(authProvider)
//                .build();
//    }
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .cors(httpCors -> httpCors.configurationSource(customCorsConfiguration()))
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(httpSession -> httpSession.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(httpAuthorization -> httpAuthorization.requestMatchers(
//                        AntPathRequestMatcher.antMatcher("/swagger-ui/index.html"),
//                        AntPathRequestMatcher.antMatcher("/swagger-ui/*"),
//                        AntPathRequestMatcher.antMatcher("/v3/api-docs/*"),
//                        AntPathRequestMatcher.antMatcher("/v3/api-docs"),
//                        AntPathRequestMatcher.antMatcher("/v1/auth/**"),
//                        AntPathRequestMatcher.antMatcher("/v1/auth/*"),
//                        AntPathRequestMatcher.antMatcher("/v1/public/**"),
//                        AntPathRequestMatcher.antMatcher("/v1/public/*")).permitAll())
//                .authorizeHttpRequests(httpAuthorization -> httpAuthorization.anyRequest().authenticated())
//                .exceptionHandling(excHandler -> excHandler.authenticationEntryPoint(authenticationEntryPoint))
//                .build();
//    }
//}
