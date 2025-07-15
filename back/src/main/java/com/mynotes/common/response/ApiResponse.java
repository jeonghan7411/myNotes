package com.mynotes.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * API 응답 통일 클래스
 * 모든 API 응답은 이 형태로 통일
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    
    private boolean success;
    private String message;
    private T data;
    private ErrorInfo error;
    
    // 성공 응답 생성 메서드들
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "성공", data, null);
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }
    
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(true, "성공", null, null);
    }
    
    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(true, message, null, null);
    }
    
    // 실패 응답 생성 메서드들
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, null, new ErrorInfo("UNKNOWN_ERROR", message));
    }
    
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(false, null, null, new ErrorInfo(code, message));
    }
    
    /**
     * 에러 정보 클래스
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorInfo {
        private String code;
        private String message;
    }
} 