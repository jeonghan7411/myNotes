package com.mynotes.controller;

import com.mynotes.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 헬스체크 컨트롤러
 * 서버 상태 확인 및 기본 동작 테스트용
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * 기본 헬스체크
     * GET /api/health
     */
    @GetMapping
    public ApiResponse<Map<String, Object>> health() {
        log.info("헬스체크 API 호출됨");
        
        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("timestamp", LocalDateTime.now());
        healthInfo.put("application", "mynotes-api");
        healthInfo.put("version", "1.0.0");
        
        return ApiResponse.success("서버가 정상적으로 동작 중입니다", healthInfo);
    }

    /**
     * 데이터베이스 연결 확인
     * GET /api/health/db
     */
    @GetMapping("/db")
    public ApiResponse<Map<String, Object>> databaseHealth() {
        log.info("데이터베이스 헬스체크 API 호출됨");
        
        Map<String, Object> dbInfo = new HashMap<>();
        dbInfo.put("database", "MariaDB");
        dbInfo.put("status", "연결 예정"); // 실제 DB 연결 후 변경
        dbInfo.put("timestamp", LocalDateTime.now());
        
        return ApiResponse.success("데이터베이스 상태 확인", dbInfo);
    }
    
    /**
     * Redis 연결 확인
     * GET /api/health/redis
     */
    @GetMapping("/redis")
    public ApiResponse<Map<String, Object>> redisHealth() {
        log.info("Redis 헬스체크 API 호출됨");
        
        Map<String, Object> redisInfo = new HashMap<>();
        redisInfo.put("cache", "Redis");
        redisInfo.put("status", "연결 예정"); // 실제 Redis 연결 후 변경
        redisInfo.put("timestamp", LocalDateTime.now());
        
        return ApiResponse.success("Redis 상태 확인", redisInfo);
    }
} 