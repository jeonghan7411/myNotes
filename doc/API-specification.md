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
        "bookmarkCount": 12,
        "isLiked": false,
        "isBookmarked": true,
        "bookmarkInfo": {
          "bookmarkId": 8,
          "folder": {
            "folderId": 5,
            "name": "Hooks",
            "fullPath": "프론트엔드/React/Hooks"
          },
          "visibility": "PRIVATE"
        },
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
    "bookmarkCount": 12,
    "isLiked": false,
    "isBookmarked": true,
    "bookmarkInfo": {
      "bookmarkId": 8,
      "folder": {
        "folderId": 5,
        "name": "Hooks",
        "fullPath": "프론트엔드/React/Hooks"
      },
      "visibility": "PRIVATE"
    },
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

## 담기/북마크 관련 API

### 1. 게시글 담기/담기 취소
```
POST /api/posts/{postId}/bookmark
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "folderId": 5,                           // 필수, 담기할 폴더 ID
  "notes": "나중에 꼭 다시 봐야할 내용",      // 선택사항, 개인 메모
  "visibility": "PRIVATE"                  // 선택사항, PUBLIC/PRIVATE (기본: PRIVATE)
}

Response:
{
  "success": true,
  "message": "담기했습니다.",
  "data": {
    "bookmarkId": 25,
    "isBookmarked": true,
    "bookmarkCount": 12,  // 해당 게시글의 총 담기 수
    "folder": {
      "folderId": 5,
      "name": "React > Hooks",
      "fullPath": "프론트엔드/React/Hooks"
    },
    "visibility": "PRIVATE"
  }
}
```

### 2. 내 담기 목록 조회
```
GET /api/bookmarks?page=0&size=10&folderId=5&visibility=PRIVATE
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "content": [
      {
        "bookmarkId": 1,
        "post": {
          "postId": 5,
          "title": "React Hook 완벽 가이드",
          "summary": "React Hook의 모든 것을 담았습니다",
          "author": {
            "userId": 10,
            "username": "reactmaster",
            "nickname": "리액트마스터"
          },
          "category": {
            "categoryId": 1,
            "name": "프론트엔드",
            "colorCode": "#3B82F6"
          },
          "likeCount": 25,
          "commentCount": 8,
          "createdAt": "2024-01-10T15:30:00Z"
        },
        "folder": {
          "folderId": 5,
          "name": "Hooks",
          "fullPath": "프론트엔드/React/Hooks",
          "colorCode": "#3B82F6",
          "icon": "📚"
        },
        "notes": "나중에 꼭 다시 봐야할 내용",
        "visibility": "PRIVATE",
        "bookmarkedAt": "2024-01-15T09:20:00Z"
      }
    ],
    "totalElements": 45,
    "totalPages": 5,
    "currentPage": 0
  }
}
```

### 3. 담기 폴더 트리 조회 (계층형)
```
GET /api/bookmark-folders/tree?includeCount=true&visibility=ALL
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": [
    {
      "folderId": 1,
      "name": "프론트엔드",
      "description": "프론트엔드 개발 관련 자료",
      "colorCode": "#3B82F6",
      "icon": "🎨",
      "visibility": "PRIVATE",
      "bookmarkCount": 35,
      "sortOrder": 1,
      "children": [
        {
          "folderId": 2,
          "name": "React",
          "description": "React 학습 자료",
          "colorCode": "#61DAFB",
          "icon": "⚛️",
          "visibility": "PUBLIC",  // 이 폴더는 공개
          "bookmarkCount": 18,
          "sortOrder": 1,
          "children": [
            {
              "folderId": 5,
              "name": "Hooks",
              "colorCode": "#61DAFB",
              "icon": "🪝",
              "visibility": "PRIVATE",
              "bookmarkCount": 8,
              "sortOrder": 1,
              "children": []
            }
          ]
        },
        {
          "folderId": 3,
          "name": "Vue.js",
          "colorCode": "#4FC08D",
          "icon": "💚",
          "visibility": "PRIVATE",
          "bookmarkCount": 12,
          "sortOrder": 2,
          "children": []
        }
      ]
    },
    {
      "folderId": 4,
      "name": "백엔드",
      "description": "백엔드 개발 관련 자료",
      "colorCode": "#10B981",
      "icon": "⚙️",
      "visibility": "PRIVATE",
      "bookmarkCount": 25,
      "sortOrder": 2,
      "children": []
    }
  ]
}
```

### 4. 폴더 생성
```
POST /api/bookmark-folders
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "name": "TypeScript",
  "description": "TypeScript 학습 자료 모음",
  "parentFolderId": 2,        // 선택사항, React 폴더 하위에 생성
  "colorCode": "#3178C6",
  "icon": "📘",
  "visibility": "PRIVATE",    // PUBLIC/PRIVATE
  "sortOrder": 3
}

Response:
{
  "success": true,
  "message": "폴더가 생성되었습니다.",
  "data": {
    "folderId": 6,
    "name": "TypeScript",
    "fullPath": "프론트엔드/React/TypeScript",
    "parentFolderId": 2,
    "colorCode": "#3178C6",
    "icon": "📘",
    "visibility": "PRIVATE"
  }
}
```

### 5. 폴더 수정
```
PUT /api/bookmark-folders/{folderId}
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "name": "TypeScript 고급",
  "description": "TypeScript 고급 학습 자료",
  "colorCode": "#3178C6",
  "icon": "📚",
  "visibility": "PUBLIC",
  "sortOrder": 1
}

Response:
{
  "success": true,
  "message": "폴더가 수정되었습니다.",
  "data": {
    "folderId": 6,
    "name": "TypeScript 고급",
    "fullPath": "프론트엔드/React/TypeScript 고급"
  }
}
```

### 6. 폴더 이동 (부모 폴더 변경)
```
PUT /api/bookmark-folders/{folderId}/move
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "newParentFolderId": 4,  // 백엔드 폴더로 이동, null이면 최상위로
  "sortOrder": 1
}

Response:
{
  "success": true,
  "message": "폴더가 이동되었습니다.",
  "data": {
    "folderId": 6,
    "oldPath": "프론트엔드/React/TypeScript",
    "newPath": "백엔드/TypeScript"
  }
}
```

### 7. 폴더 삭제
```
DELETE /api/bookmark-folders/{folderId}?moveBookmarksTo=4
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "message": "폴더가 삭제되었습니다. 담기 항목들은 다른 폴더로 이동되었습니다.",
  "data": {
    "deletedFolderId": 6,
    "movedBookmarksCount": 12,
    "targetFolderId": 4
  }
}
```

### 8. 담기 메모 및 설정 수정
```
PUT /api/bookmarks/{bookmarkId}
Authorization: Bearer {accessToken}
Content-Type: application/json

Request Body:
{
  "folderId": 4,              // 다른 폴더로 이동
  "notes": "수정된 메모 내용",
  "visibility": "PUBLIC"      // 공개로 변경
}

Response:
{
  "success": true,
  "message": "담기 정보가 수정되었습니다.",
  "data": {
    "bookmarkId": 1,
    "folder": {
      "folderId": 4,
      "name": "백엔드",
      "fullPath": "백엔드"
    },
    "notes": "수정된 메모 내용",
    "visibility": "PUBLIC"
  }
}
```

### 9. 담기 삭제
```
DELETE /api/bookmarks/{bookmarkId}
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "message": "담기를 해제했습니다."
}
```

### 10. 게시글의 담기 상태 확인
```
GET /api/posts/{postId}/bookmark-status
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "isBookmarked": true,
    "bookmarkId": 5,
    "folder": {
      "folderId": 2,
      "name": "React",
      "fullPath": "프론트엔드/React"
    },
    "visibility": "PRIVATE",
    "bookmarkCount": 12  // 해당 게시글의 총 담기 수 (공개 담기만)
  }
}
```

## 공개 담기 탐색 API

### 1. 공개 담기 컬렉션 탐색
```
GET /api/explore/public-bookmarks?page=0&size=10&sortBy=POPULAR
Authorization: Bearer {accessToken}

Query Parameters:
- sortBy: POPULAR, RECENT, BOOKMARK_COUNT
- categoryId: 카테고리 필터

Response:
{
  "success": true,
  "data": {
    "content": [
      {
        "bookmarkId": 15,
        "post": {
          "postId": 8,
          "title": "React 성능 최적화 완벽 가이드",
          "summary": "React 앱 성능을 극적으로 향상시키는 방법들",
          "author": {
            "userId": 20,
            "username": "reactpro",
            "nickname": "리액트프로"
          }
        },
        "bookmarkOwner": {
          "userId": 15,
          "username": "frontend_guru",
          "nickname": "프론트엔드구루",
          "followerCount": 1200
        },
        "folder": {
          "folderId": 12,
          "name": "성능 최적화",
          "fullPath": "프론트엔드/React/성능 최적화",
          "colorCode": "#FF6B6B",
          "icon": "⚡"
        },
        "curatorNote": "실제 프로젝트에서 활용해본 검증된 최적화 기법들",
        "bookmarkCount": 89,  // 이 게시글을 담기한 총 사용자 수
        "bookmarkedAt": "2024-01-12T14:30:00Z"
      }
    ],
    "totalElements": 256,
    "totalPages": 26,
    "currentPage": 0
  }
}
```

### 2. 사용자의 공개 담기 컬렉션 조회
```
GET /api/users/{userId}/public-bookmarks?page=0&size=10&folderId=12
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "user": {
      "userId": 15,
      "username": "frontend_guru",
      "nickname": "프론트엔드구루",
      "followerCount": 1200,
      "totalPublicBookmarks": 145
    },
    "folder": {
      "folderId": 12,
      "name": "성능 최적화",
      "fullPath": "프론트엔드/React/성능 최적화",
      "description": "실무에서 검증된 React 성능 최적화 자료집",
      "colorCode": "#FF6B6B",
      "icon": "⚡"
    },
    "bookmarks": [
      // 담기 목록...
    ]
  }
}
```

### 3. 공개 폴더 트리 조회
```
GET /api/users/{userId}/public-folders/tree
Authorization: Bearer {accessToken}

Response:
{
  "success": true,
  "data": {
    "user": {
      "userId": 15,
      "username": "frontend_guru",
      "nickname": "프론트엔드구루"
    },
    "folders": [
      // 공개 설정된 폴더들의 계층 구조...
    ]
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