# URL Shortening Service
- URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
<br>: 예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro

<img width="645" alt="스크린샷 2021-05-23 오전 2 12 45" src="https://user-images.githubusercontent.com/24769585/119267435-8a9a9680-bc29-11eb-99ea-2e1d2fd383d7.png">

### 요구사항
* URL 입력폼 제공 및 결과 출력
* URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
* 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
* Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.

### 구현 내용
1. 테이블에 저장 시 생성되는 auto-increment 칼럼을 인코딩하여 Shortening Key를 생성한다.
	* URL 생성에 적합한 Base32 인코딩 적용
	* 최소 3자 이상의 키 생성을 위해 auto-increment 시작 값을 100000으로 지정
	* 8자 이상의 Shortening Key 출력 시 OverMaxLengthException 발생
2. redirect 처리 시 요청 수에 대해 증가 처리 후 원래 URL로 리다이렉트 한다.
3. 테스트 코드 목록
	* DTO 생성 테스트
	* URL info 정보 저장 테스트
	* 저장된 URL 조회 테스트
	* short Key 생성 테스트
	* base62 encoding 테스트 
	* 동일 short Key 생성 테스트

### 프로젝트 구성
#### 개발환경
* Mac OS X
* spring-tool-suite-4-4.10.0.RELEASE

#### 사용 기술
##### Back-End
* Java8
* Spring Boot 2.4.5
* JPA
* Lombok
* Thymeleaf
* jUnit

##### Front-End
* jquery-3.3.1.min.js

##### Database
* MySQL 

### 구동 방법
1. Requirements
	* Java 1.8
	* Gradle 6.8.3
	* MySQL 8.0.23

2. Configuration
	* application.yml
	``` 
	# database  
		spring:
  			datasource:
    			driver-class-name: com.mysql.cj.jdbc.Driver
    			url: jdbc:mysql://[MYSQL_HOST]:[MYSQL_PORT]/[DATABASE_NAME]?serverTimezone=UTC&characterEncoding=UTF-8&autoReconnect=true
    			username: [DB_USER]
    			password: [DB_PASSWORD]
    ``` 
3. Build & Run
``` 
	gradle build
	java -jar build/libs/shortening-0.0.1.jar
``` 

### 동작 방법
1. 서버 구동 시, http://localhost:8080 주소 진입
<img width="750" alt="ss0" src="https://user-images.githubusercontent.com/24769585/119268262-1bbf3c80-bc2d-11eb-82ea-a03b96899f36.png"><br>
2. 입력 창에 변환할 URL 입력 후 'URL 줄이기' 버튼 클릭
<img width="750" alt="ss1" src="https://user-images.githubusercontent.com/24769585/119268259-1b26a600-bc2d-11eb-8271-ae09e3b7a31d.png"><br>
3. 입력 값이 없으면 오류 메세지 표시
<img width="750" alt="ss2" src="https://user-images.githubusercontent.com/24769585/119268256-182bb580-bc2d-11eb-8ed0-46830b4ef6b9.png"><br>
4. 입력 형식이 http:// 혹은 https:// 가 니라면 오류 메세지 표시
<img width="750" alt="ss3" src="https://user-images.githubusercontent.com/24769585/119268258-1a8e0f80-bc2d-11eb-9119-5b537d0b4d78.png"><br>
5. URL 변환 실패 혹은 서버 오류 시 오류 메세지 표시
<img width="761" alt="ss5" src="https://user-images.githubusercontent.com/24769585/119268391-aef87200-bc2d-11eb-9b88-c51116fde1d6.png"><br>
6. 변환 URL 복사 후 브라우저에 입력하거나 Go to 버튼 입력 시 원본 URL로 이동
