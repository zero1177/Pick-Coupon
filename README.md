# PICKOO 서비스

사용자의 소비 패턴을 분석하여 지역 페이 매장 및 쿠폰을 맞춤 추천해주는 서비스

## 프로젝트 개요

PICKOO는 사용자의 소비 내역을 분석해 상위 소비 카테고리를 파악하고, 해당 카테고리에 맞는 매장과 쿠폰을 GPT 기반 추천 멘트와 함께 제공하는 웹사이트입니다. 해커톤 프로젝트로 개발되었으며, 서울 페이와 연계한 지역 소상공인 쿠폰 추천을 목표로 합니다.

## 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.4 |
| Build Tool | Gradle |
| Database | MySQL |
| ORM | Spring Data JPA |
| AI | OpenAI GPT-4o-mini |
| HTTP Client | Spring WebFlux (WebClient) |
| Security | Spring Security |
| Infra | AWS RDS, AWS EC2 (Port 8080) |
| Etc | Lombok, Spring Actuator |

## 주요 기능

- **소비 패턴 분석**: 사용자의 소비 로그를 기반으로 상위 카테고리 추출
- **지역 페이 가맹점 추천**: 상위 1, 2위 카테고리에서 각 3곳씩, 총 6곳의 매장 추천
- **쿠폰 정보 제공**: 각 가게에 연결된 쿠폰 정보 조회 가능
- **GPT 추천 멘트**: OpenAI API를 활용해 소비 패턴에 맞는 친근한 추천 문장 생성

## 프로젝트 구조

```
src/main/java/hackathon/pickCoupon/
├── recommendation/
│   ├── controller/         # API 엔드포인트
│   │   ├── RecommendationController.java
│   │   └── UserController.java
│   ├── service/            # 비즈니스 로직
│   │   ├── RecommendationService.java
│   │   ├── ConsumptionAnalysisService.java
│   │   └── GPTService.java
│   ├── domain/             # JPA 엔티티
│   │   ├── Store.java
│   │   ├── Coupon.java
│   │   ├── Menu.java
│   │   ├── User.java
│   │   ├── UserConsumptionLog.java
│   │   └── CategoryType.java
│   ├── repository/         # 데이터 접근 레이어
│   └── web/                # 응답 DTO
├── seed/                   # 초기 데이터 로더
└── CorsConfig.java
```

## 📡 API 명세

### 쿠폰 추천 조회

```
GET /api/recommendations?userId={userId}
```

**Response 예시**

```json
{
  "message": "카페에서 잠깐 쉬고, 레저로 주말의 활력을 채워보세요. ☀️",
  "stores": [
    {
      "id": 1,
      "name": "스타벅스 홍대점",
      "image": "https://...",
      "rating": 4.5,
      "address": "서울 마포구 ...",
      "phone": "02-123-4567",
      "hours": "09:00 - 22:00",
      "category": "카페",
      "payment": "서울 페이",
      "coupon_is": 1,
      "coupon": "아메리카노 1+1",
      "menu": [
        { "name": "아메리카노", "menuImage": "https://..." }
      ]
    }
  ]
}
```

### 지원 카테고리

`꽃/원예` `분식` `레저/오락시설` `베이커리/제과점` `뷰티/헤어` `서점/문구점` `양식` `원데이클래스/체험` `전시/공연` `주점/바` `중식` `카페` `패션/액세서리` `한식`

### 빌드 및 실행

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun

# 또는 JAR 직접 실행
java -jar build/libs/application.jar
```

서버 기본 포트: `8080`