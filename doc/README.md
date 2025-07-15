# 📚 학습 메모 SNS 플랫폼

## 프로젝트 개요
사용자들이 학습한 내용을 메모하고 공유할 수 있는 소셜 네트워킹 플랫폼입니다.

## 주요 기능
- 🔐 **사용자 인증**: JWT 기반 로그인/회원가입
- 📝 **개인 학습 메모**: 사용자별 학습 내용 작성 및 관리
- 👥 **소셜 기능**: 팔로우, 좋아요, 댓글 시스템
- 📱 **피드**: 팔로우한 사용자들의 게시글 타임라인
- 👑 **관리자 기능**: 컨텐츠 관리, 사용자 관리, 접속 로그, 공지사항

## 기술 스택

### Frontend
- React 18+ with TypeScript
- 상태 관리: Zustand (클라이언트) + TanStack Query (서버)
- 스타일링: Tailwind CSS
- HTTP 클라이언트: Axios
- UI 컴포넌트: 커스텀 컴포넌트 + Headless UI

### Backend
- Spring Boot 3.x
- Spring Security + JWT
- MyBatis (SQL Mapper)
- Redis (캐시)
- MariaDB

### 인프라
- Docker & Docker Compose
- 추후 클라우드 배포 예정

## 프로젝트 구조
```
myNotes/
├── front/          # React TypeScript 앱
├── back/           # Spring Boot API 서버
└── doc/            # 프로젝트 문서
```

## 개발 일정
1. 프로젝트 설계 및 문서화
2. 백엔드 초기 설정 및 인증 구현
3. 프론트엔드 초기 설정 및 기본 UI
4. 핵심 기능 구현 (게시글, 소셜 기능)
5. 관리자 페이지 구현
6. 컨테이너화 및 배포 