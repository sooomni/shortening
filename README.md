# URL Shortening Service

### 요구사항
* URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
<br>: 예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro
* URL 입력폼 제공 및 결과 출력
* URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
* 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
* Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.


### Build & Run
``` 
	gradle build
	java -jar build/libs/shortening-0.0.1.jar
``` 
