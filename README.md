# 📝 다 써봐 게시판 프로젝트

Spring Boot 기반의 OAuth2 로그인 기능과 게시글 CRUD를 구현한 웹 애플리케이션입니다.

## 📎 프로젝트 포트폴리오

👉 [📄 포트폴리오 PDF 보기](./portfolio.pdf)

포트폴리오에는 전체 아키텍처, 기능 설명, 트러블슈팅, 느낀점 등이 정리되어 있습니다.

## 💡 주요 기능
- Google, Kakao 이메일로 로그인 (OAuth2)
- 게시글 등록, 수정, 삭제
- 게시글 검색 및 조회
- JWT 기반 인증 및 Spring Security 적용

## 📂 기술 스택
- Spring Boot, Spring Security, Spring Data JPA
- OAuth2 Client (Google, Kakao)
- H2 Database
- Gradle
- IntelliJ, Postman

## 🔧 실행 방법
```bash
git clone https://github.com/heo-jaeyeon/spring-boot-board.git
cd spring-boot-board
./gradlew bootRun
