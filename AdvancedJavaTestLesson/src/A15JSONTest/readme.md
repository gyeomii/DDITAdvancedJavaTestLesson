# JSONTest
(JavaScript Object Notation)

## JSON VS XML

- XML은 종료태그를 사용하기 때문에 JSON보다 용량이 크다.
- 읽고 쓰는 속도가 JSON이 XML보다 빠르다.
- XML이 JSON보다 안정성이 높다.

<hr>

### JSON 구조

```java

{"object":[
	{"key1" : "value1", "key2" : "value2"}
	{"key1" : "value3", "key2" : "value4"}
	{"key1" : "value5", "key2" : "value6"}
]}

```
# JSON의 특징

- 사용하기 쉽다
- 적은 메모리 공간을 사용하기 때문에 빠르다
- 맵핑을 생성하지 않아도된다(Jackson API가 직렬화 할 여러 개체에 대한 기본 매핑을 제공한다)
- 종속성(JSON을 처리하기 위해 다른 라이브러리가 필요하지 않다)

# XML의 특징
- XML 태그는 미리 정의되어 있지 않다. 사용자 정의 태그를 정의해야한다
- 사람이 이해하기 쉽다
- 구조화 된 형식은 프로그램에서 읽고 쓰기가 쉽다
- XML은 HTML과 같은 확장 가능한 마크업 언어이다
- 데이터를 전달하도록 설계 되어있고 데이터를 표시할 수 없다

#JSON 장점
모든 브라우저에 대한 지원 제공
생성, 조작, 읽기 , 쓰기가 쉽다
구문이 간단
javascript에서 기본적으로 인식되고 javascript 함수인 eval() 로 구문 분석이 가능하다
직렬화가 가능하다
JavaScript 의 모든 객체를 JSON으로 변환하여 JSON을 서버로 보낼 수 있는 텍스트이다
XML 장점
시스템 및 애플리케이션간에 문서 전송이 가능하다.
서로 다른 플랫폼 간에 데이터 교환이 가능하다
HTML에서 데이터를 분리한다
플랫폼 변경 프로세스를 단순화한다
JSON의 단점
네임 스페이스 지원이 없다. ( 확장성이 부족)
형식적인 문법 정의 지원( 문법을 지켜야한다)
제한된 개발 도구 지원
XML의 단점
처리 응용 프로그램이 필요하다
내장 데이터 유형 지원이 없다
XML 구문이 중복된다
사용자가 자신의 태그를 생성하는 것을 허용하지 않는다
텍스트 기반 데이터 전송 형식과 유사하다