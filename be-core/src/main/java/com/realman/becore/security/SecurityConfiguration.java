package com.realman.becore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.realman.becore.security.filter.JwtAuthenticationFilter;
import com.realman.becore.security.password_encoder.AppPasswordEncoder;
import com.realman.becore.security.user_detail_service.CustomUserDetailService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    @NonNull
    private AppPasswordEncoder appPasswordEncoder;

    @NonNull
    private CustomUserDetailService customUserDetailService;

    @NonNull
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(appPasswordEncoder.passwordEncoder());
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(daoAuthenticationProvider).build();
    }

    private CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/v1/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(t -> t.configurationSource(configurationSource())).csrf(t -> t.disable())
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        t -> t.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/index.html"),
                                AntPathRequestMatcher.antMatcher("/swagger-ui/*"),
                                AntPathRequestMatcher.antMatcher("/v3/api-docs/*"),
                                AntPathRequestMatcher.antMatcher("/v3/api-docs"),
                                AntPathRequestMatcher.antMatcher("/v1/account/**"),
                                AntPathRequestMatcher.antMatcher("/v1/otp/**")).permitAll())

                .authorizeHttpRequests(t -> t.anyRequest().authenticated())

                .build();
    }
}
