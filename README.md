# 국가유공자 검색 서비스

&nbsp; 이 프로젝트는 국가보훈처 공훈전자사료관의 오픈 API를 이용해 유공자를 검색하기 위한 서비스를 구현하는 것을 목표로 하였습니다. 총 14개의 변수를 이용해 국가 유공자에 대한 정보를 효율적으로 찾고 관련 정보를 조회할 수 있는 서비스입니다. 더 나아가 기존에는 없는 특별한 검색 기능, 생애시기별 조회와 다중상세검색을 제작하였습니다.
 
<br>

---

## 프로젝트 개요

<table class="tg">
    <tr align="left">
      <th>프로젝트명</th>
      <th>국가유공자 조회 서비스</th>
    </tr>
    <tr align="left">
      <th>개발기간</th>
      <th>2021.02.25 ~ 2021.03.03</th>
    </tr>
    <tr align="left">
      <th>인원</th>
      <th>3</th>
    </tr>
    <tr>
      <th colspan="2">사용 기술</th>
    </tr>
    <tr align="left">
      <th>백엔드</th>
      <th>Java, Oracle DB</th>
    </tr>
    <tr align="left">
      <th>형상관리</th>
      <th>Dropbox</th>
    </tr>
    <tr align="left">
      <th>툴</th>
      <th>Eclipse</th>
    </tr>
    <tr align="left">
      <th>운영체제</th>
      <th>Windows 10</th>
    </tr>
  </table>

<br>

---
## 데이터 설명
- 전자사료관 내에서 제공하는 데이터 : 한글 이름, 한자 이름, 이명, 생년월일, 사망년월일, 성별, 본적 대분류, 본적 중분류, 본적 소분류, 포상년도, 훈격, 운동계열, 공적 개요(한문), 공적 개요(국한문 혼용)
- 프로젝트에서 활용한 데이터 : 한글 이름, 한자 이름, 이명, 생년월일, 사망년월일, 성별, 본적 대분류, 본적 중분류, 본적 소분류, 포상년도, 훈격, 운동계열, 공적 개요(국한문 혼용)

## 활용한 기술
- Java : Maven Framework, Jsoup, JDBC, MVC/Singleton Pattern, 
- Oracle DB : SQL query, PLSQL, DML, DLL

## 기능설명

&nbsp; 이클립스 내에서 실행을 하게 되면 admin과 user를 나누어서 접근해야 합니다.

1. 관리자용(admin)
 - 01. 명령프롬프트 열기 : cmd창으로 트리거 권한부여를 위해 사용, 쓰레드가 발생하기 때문에 사용 후 완전히 종료해야 함
 - 02. DB테이블 생성하기 : 기존 테이블을 제거하고 새로운 테이블을 생성함
 - 03. 데이터 크롤링 진행하기 : 전자사료관 웹페이지를 돌아다니면서 크롤링 이후 데이터베이스에 인서트
 - 04. 국가유공자 테이블 접근
  - 국가 유공자 테이블 전체 조회
  - 국가 유공자 테이블 관리번호로 조회
  - 국가 유공자 테이블 추가
  - 국가 유공자 테이블 관리번호로 추가

2. 사용자용(user)








<br/>
