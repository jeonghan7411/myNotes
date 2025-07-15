package com.mynotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정
 * JWT 인증과 권한 관리를 위한 설정
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 비활성화 (API 서버이므로)
            .csrf(csrf -> csrf.disable())
            
            // 요청 권한 설정
            .authorizeHttpRequests(authz -> authz
                // 헬스체크는 인증 없이 접근 가능
                .requestMatchers("/health/**").permitAll()
                
                // 인증 관련 API는 인증 없이 접근 가능 (추후 추가)
                .requestMatchers("/auth/**").permitAll()
                
                // 나머지는 모든 요청에 인증 필요
                .anyRequest().authenticated()
            )
            
            // 기본 로그인 폼 사용 (개발 중에만, 추후 JWT로 변경)
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            
            // 로그아웃 설정
            .logout(logout -> logout.permitAll());

        return http.build();
    }
} 