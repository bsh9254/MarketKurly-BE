![](../../Desktop/Desktop_image/springlogo.png)
## MarketKurly Clone Project


## 🍇 구현 기능
* 회원가입
* 로그인
* 메인 페이지
* 상세 페이지
* 장바구니

## 🗓 프로젝트 기간
2022년 9월 9일 ~ 2022년 9월 15일

## 👻 팀 소개
#### `Backend`
- 안재원
- 정수민
- 이수민
- 배상훈

## 📜 기술스택
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

## 🐳  ERD
![Untitled (1)](https://user-images.githubusercontent.com/110365670/190194717-3f4b02c5-e21d-4842-9a50-5d747dded60a.png)

## 🏹 Trouble Shooting
- Swagger를 설정했으나 접근이 안되는 문제가 발생
=> addResourceHandlers를 오버라이딩 해서 해결함

- 컬럼명이 카멜표기법과 스네이크표기법으로 서로 다른부분이 있어서 컬럼명을 찾을 수 없는 문제가 발생
=> 카멜표기법으로 통일하여 컬럼명을 작성해서 해결함

- OAuth2(Google) 로그인 구현 중 입력받은 정보와 userDetatils의 정보와 비교해 인증을 하는 과정에서 오류
=> 비교 인증을 제외하고 입력받은 내용을 바로 UsernamePasswordAuthenticationToken로 만들어서 토큰을 발급함 

- 로그아웃을 진행할 때 Refresh Token Entity상 doesn't have defaul value 에러 생성
=>Redis를 이용해 로그아웃 구현. 하지만 그냥 DB를 정리해주는 것으로 해결 가능.

## 🔨 API 설계 
https://powerful-iguana-95d.notion.site/02b032f53264463ba47ae5b6e1e2431e?v=4c2d2cdb2cfd4a94b74eb4ed69927724
