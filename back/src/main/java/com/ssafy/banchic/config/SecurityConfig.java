package com.ssafy.banchic.config;

import com.ssafy.banchic.exception.AccessDeniedHandlerException;
import com.ssafy.banchic.exception.AuthenticationEntryPointException;
import com.ssafy.banchic.security.JwtFilter;
import com.ssafy.banchic.service.UserDetailsServiceImpl;
import com.ssafy.banchic.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    @Value("${jwt.secret}")
    String SECRET_KEY;
    private final TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//            .cors(corsCustomizer  -> corsCustomizer.configurationSource(corsConfigurationSource()))
            .csrf(CsrfConfigurer::disable)
            .headers((headerConfig) ->
                headerConfig.frameOptions(FrameOptionsConfig::disable)
            )
            .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                    .requestMatchers( "/","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers("/auth/login/**").permitAll()
                    .requestMatchers("/auth/test/**").permitAll()
                    .requestMatchers("/perfume/**").permitAll()
                    .anyRequest().permitAll()
            )
            .exceptionHandling(authenticationManager -> authenticationManager
                .authenticationEntryPoint(new AuthenticationEntryPointException())
                .accessDeniedHandler(new AccessDeniedHandlerException()))
            .addFilterBefore(new JwtFilter(SECRET_KEY, tokenProvider, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(List.of(
//            "http://localhost:5173",
//            "http://j10b109.p.ssafy.io:5173"
//        ));
//        config.setAllowedHeaders(List.of("*"));
//        config.setExposedHeaders(List.of("*"));
//        config.setAllowedMethods(Arrays.asList(
//            HttpMethod.GET.name(),
//            HttpMethod.POST.name(),
//            HttpMethod.DELETE.name(),
//            HttpMethod.PUT.name(),
//            HttpMethod.HEAD.name(),
//            HttpMethod.OPTIONS.name()
//        ));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

}