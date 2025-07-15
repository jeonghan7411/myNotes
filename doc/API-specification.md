# 🔌 API 명세서

## 인증 관련 API

### 1. 회원가입
```
POST /api/auth/signup
Content-Type: application/json

Request Body:
{
  "email": "user@example.com",
  "username": "username",
  "password": "password123",
  "nickname": "닉네임"
}

Response:
{
  "success": true,
  "message": "회원가입이 완료되었습니다.",
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "username": "username",
    "nickname": "닉네임"
  }
}
```

### 2. 로그인
```
POST /api/auth/login
Content-Type: application/json

Request Body:
{
  "email": "user@example.com",
  "password": "password123"
}

Response:
{
  "success": true,
  "message": "로그인 성공",
  "data": {
    "accessToken": "jwt_access_token",
    "refreshToken": "jwt_refresh_token",
    "user": {
      "userId": 1,
      "email": "user@example.com",
      "username": "username",
      "nickname": "닉네임",
      "role": "USER"
    }
  }
}
```

### 3. 토큰 갱신
```
POST /api/auth/refresh
Content-Type: application/json

Request Body:
{
  "refreshToken": "jwt_refresh_token"
}

Response:
{
  "success": true,
  "data": {
    "accessToken": "new_jwt_access_token",
    "refreshToken": "new_jwt_refresh_token"
  }
}
```

## 사용자 관련 API

### 1. 내 프로필 조회
```
GET /api/users/me
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "username": "username",
    "nickname": "닉네임",
    "bio": "자기소개",
    "profileImageUrl": "https://...",
    "followerCount": 10,
    "followingCount": 5,
    "postCount": 20
  }
}
```

### 2. 다른 사용자 프로필 조회
```
GET /api/users/{userId}
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "userId": 2,
    "username": "otheruser",
    "nickname": "다른사용자",
    "bio": "자기소개",
    "profileImageUrl": "https://...",
    "followerCount": 15,
    "followingCount": 8,
    "postCount": 30,
    "isFollowing": false
  }
}
```

### 3. 팔로우/언팔로우
```
POST /api/users/{userId}/follow
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "message": "팔로우했습니다.",
  "data": {
    "isFollowing": true
  }
}
```

## 게시글 관련 API

### 1. 게시글 목록 조회 (피드)
```
GET /api/posts/feed?page=0&size=10
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "content": [
      {
        "postId": 1,
        "title": "React Hook 학습 정리",
        "content": "오늘 배운 내용...",
        "summary": "React Hook에 대한 기본 개념",
        "tags": ["React", "Hook", "Frontend"],
        "author": {
          "userId": 2,
          "username": "reactdev",
          "nickname": "리액트개발자",
          "profileImageUrl": "https://..."
        },
        "category": {
          "categoryId": 1,
          "name": "프론트엔드",
          "colorCode": "#3B82F6"
        },
        "likeCount": 5,
        "commentCount": 3,
        "isLiked": false,
        "createdAt": "2024-01-15T10:30:00Z",
        "updatedAt": "2024-01-15T10:30:00Z"
      }
    ],
    "totalElements": 100,
    "totalPages": 10,
    "currentPage": 0
  }
}
```

### 2. 게시글 작성
```
POST /api/posts
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "title": "Spring Boot 학습 정리",
  "content": "오늘 배운 Spring Boot 내용을 정리해보자...",
  "summary": "Spring Boot 기본 개념 정리",
  "categoryId": 2,
  "tags": ["Spring", "Boot", "Backend"],
  "visibility": "PUBLIC"
}

Response:
{
  "success": true,
  "message": "게시글이 작성되었습니다.",
  "data": {
    "postId": 5,
    "title": "Spring Boot 학습 정리",
    "createdAt": "2024-01-15T11:00:00Z"
  }
}
```

### 3. 게시글 상세 조회
```
GET /api/posts/{postId}
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "postId": 1,
    "title": "React Hook 학습 정리",
    "content": "상세한 내용...",
    "summary": "React Hook에 대한 기본 개념",
    "tags": ["React", "Hook", "Frontend"],
    "author": {
      "userId": 2,
      "username": "reactdev",
      "nickname": "리액트개발자"
    },
    "category": {
      "categoryId": 1,
      "name": "프론트엔드"
    },
    "likeCount": 5,
    "commentCount": 3,
    "isLiked": false,
    "visibility": "PUBLIC",
    "createdAt": "2024-01-15T10:30:00Z",
    "updatedAt": "2024-01-15T10:30:00Z"
  }
}
```

## 댓글 관련 API

### 1. 댓글 목록 조회
```
GET /api/posts/{postId}/comments?page=0&size=10
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "content": [
      {
        "commentId": 1,
        "content": "좋은 정리 감사합니다!",
        "author": {
          "userId": 3,
          "username": "learner",
          "nickname": "학습자",
          "profileImageUrl": "https://..."
        },
        "parentCommentId": null,
        "replies": [
          {
            "commentId": 2,
            "content": "도움이 되셨다니 기쁩니다!",
            "author": {
              "userId": 2,
              "username": "reactdev",
              "nickname": "리액트개발자"
            },
            "parentCommentId": 1,
            "createdAt": "2024-01-15T11:15:00Z"
          }
        ],
        "createdAt": "2024-01-15T11:10:00Z"
      }
    ],
    "totalElements": 3,
    "totalPages": 1,
    "currentPage": 0
  }
}
```

### 2. 댓글 작성
```
POST /api/posts/{postId}/comments
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "content": "유용한 정보 감사합니다!",
  "parentCommentId": null
}

Response:
{
  "success": true,
  "message": "댓글이 작성되었습니다.",
  "data": {
    "commentId": 5,
    "content": "유용한 정보 감사합니다!",
    "createdAt": "2024-01-15T12:00:00Z"
  }
}
```

## 좋아요 관련 API

### 1. 좋아요/취소
```
POST /api/posts/{postId}/like
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "message": "좋아요했습니다.",
  "data": {
    "isLiked": true,
    "likeCount": 6
  }
}
```

## 카테고리 관련 API

### 1. 카테고리 목록 조회
```
GET /api/categories

Response:
{
  "success": true,
  "data": [
    {
      "categoryId": 1,
      "name": "프론트엔드",
      "description": "프론트엔드 개발 관련",
      "colorCode": "#3B82F6"
    },
    {
      "categoryId": 2,
      "name": "백엔드",
      "description": "백엔드 개발 관련",
      "colorCode": "#10B981"
    }
  ]
}
```

## 관리자 API

### 1. 대시보드 통계
```
GET /api/admin/dashboard/stats
Authorization: Bearer {adminAccessToken}

Response:
{
  "success": true,
  "data": {
    "totalUsers": 1250,
    "totalPosts": 3500,
    "totalComments": 8900,
    "dailyActiveUsers": 150,
    "weeklyNewUsers": 45,
    "monthlyNewPosts": 230
  }
}
```

### 2. 사용자 관리
```
GET /api/admin/users?page=0&size=20&status=ACTIVE
Authorization: Bearer {adminAccessToken}

Response:
{
  "success": true,
  "data": {
    "content": [
      {
        "userId": 1,
        "email": "user@example.com",
        "username": "username",
        "nickname": "닉네임",
        "status": "ACTIVE",
        "role": "USER",
        "createdAt": "2024-01-01T00:00:00Z",
        "lastLoginAt": "2024-01-15T09:00:00Z"
      }
    ],
    "totalElements": 1250,
    "totalPages": 63,
    "currentPage": 0
  }
}
```

## 에러 응답 형식

```
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "입력값이 올바르지 않습니다.",
    "details": [
      {
        "field": "email",
        "message": "이메일 형식이 올바르지 않습니다."
      }
    ]
  }
}
```

## HTTP 상태 코드

- `200 OK`: 성공
- `201 Created`: 생성 성공
- `400 Bad Request`: 잘못된 요청
- `401 Unauthorized`: 인증 실패
- `403 Forbidden`: 권한 없음
- `404 Not Found`: 리소스 없음
- `409 Conflict`: 중복 데이터
- `500 Internal Server Error`: 서버 오류 