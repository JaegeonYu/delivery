# Delievery Application
## 배우고 적용한 것
- [ ] DDD 레이어 아키텍쳐 적용, 도메인(엔티티) 계층에서 비즈니스 로직 구현 후 서비스 계층에선 비즈니스 로직 호출
- [ ] API URL, HTTP METHOD RESTful한 설계, Response HTTP Status CODE 정상 응답시에도, 예외처리시에도 Status Code 분리
- [ ] 연관관계 매핑@과 @JoinColumn을 통한 엔티티 연관관계 구축
- [ ] N+1 문제를 해결하고 Data JPA가 제공하지 않는 쿼리 구현을 위한 Fetch Join JPQL 사용
- [ ] DTO <-> Entity 변경로직의 위치 고민(컨트롤러 계층, 서비스 계층) > 서비스는 다른 서비스에서 호출할 수 있고 다른 컨트롤러에서도 호출할 수 있기에 특정 컨트롤러의 DTO에 의존하게 되면 안될 것 같아 컨트롤러에 위치, 가장 좋은 방법은 계층마다 DTO 만들기
- [ ] Service 계층 테스트 코드 작성
- [ ] RuntimeException 상속하는 커스텀 예외처리 클래스 사용 및 Spring Core가 제공하는 AOP 기능 중 하나인 ControllerAdvice, ExceptionHandler 사용

## 추가할 기능
- [ ] 주문 시에 여러 물품 주문
- [ ] 사용자 장바구니
- [ ] 인증, 인가

## API 설계
### Food
- [ ] 음식 전체 조회 GET : /foods
- [ ] 음식 등록 POST : /foods
- [ ] 음식 단일 조회 GET : /foods/{productId}
- [ ] 음식 수정 PATCH : /foods/{productId}
- [ ] 음식 삭제 DELETE : /foods/{productId}

### Member
- [ ] 유저 등록 POST : /members
- [ ] 유저 전체 조회 GET : /members
- [ ] 유저 단일 조회 GET : /members/{memberId}

### Order
- [ ] 주문 등록 POST : /orders
- [ ] 주문 취소 POST : /orders/{orderID}
- [ ] 주문 검색 GET : /orders?name="?"&ORDERSTATUS="?"

## 클래스 다이어그램
![DeliveryClass (1)](https://user-images.githubusercontent.com/76714219/235337625-1f973615-b8e1-4597-becd-4d3a6e883418.jpg)

## ERD 설계
![delivery](https://user-images.githubusercontent.com/76714219/235337629-988e7e05-ded5-4737-b2ea-2347d090f5dd.png)
