package com.mynotes.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 설정 클래스
 * Mapper 인터페이스들을 자동으로 스캔하여 빈으로 등록
 */
@Configuration
@MapperScan("com.mynotes.mapper")
public class MyBatisConfig {
    
    // MyBatis 관련 추가 설정이 필요한 경우 여기에 Bean 정의
    
} 