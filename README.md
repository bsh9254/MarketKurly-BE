![](../../Desktop/Desktop_image/springlogo.png)
## MarketKurly Clone Project


## ๐ ๊ตฌํ ๊ธฐ๋ฅ
* ํ์๊ฐ์
* ๋ก๊ทธ์ธ
* ๋ฉ์ธ ํ์ด์ง
* ์์ธ ํ์ด์ง
* ์ฅ๋ฐ๊ตฌ๋

## ๐ ํ๋ก์ ํธ ๊ธฐ๊ฐ
2022๋ 9์ 9์ผ ~ 2022๋ 9์ 15์ผ

## ๐ป ํ ์๊ฐ
#### `Backend`
- ์์ฌ์
- ์ ์๋ฏผ
- ์ด์๋ฏผ
- ๋ฐฐ์ํ

## ๐ ๊ธฐ์ ์คํ
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

## ๐ณ  ERD
![Untitled (1)](https://user-images.githubusercontent.com/110365670/190194717-3f4b02c5-e21d-4842-9a50-5d747dded60a.png)

## ๐น Trouble Shooting
- Swagger๋ฅผ ์ค์ ํ์ผ๋ ์ ๊ทผ์ด ์๋๋ ๋ฌธ์ ๊ฐ ๋ฐ์
=> addResourceHandlers๋ฅผ ์ค๋ฒ๋ผ์ด๋ฉ ํด์ ํด๊ฒฐํจ

- ์ปฌ๋ผ๋ช์ด ์นด๋ฉํ๊ธฐ๋ฒ๊ณผ ์ค๋ค์ดํฌํ๊ธฐ๋ฒ์ผ๋ก ์๋ก ๋ค๋ฅธ๋ถ๋ถ์ด ์์ด์ ์ปฌ๋ผ๋ช์ ์ฐพ์ ์ ์๋ ๋ฌธ์ ๊ฐ ๋ฐ์
=> ์นด๋ฉํ๊ธฐ๋ฒ์ผ๋ก ํต์ผํ์ฌ ์ปฌ๋ผ๋ช์ ์์ฑํด์ ํด๊ฒฐํจ

- OAuth2(Google) ๋ก๊ทธ์ธ ๊ตฌํ ์ค ์๋ ฅ๋ฐ์ ์ ๋ณด์ userDetatils์ ์ ๋ณด์ ๋น๊ตํด ์ธ์ฆ์ ํ๋ ๊ณผ์ ์์ ์ค๋ฅ
=> ๋น๊ต ์ธ์ฆ์ ์ ์ธํ๊ณ  ์๋ ฅ๋ฐ์ ๋ด์ฉ์ ๋ฐ๋ก UsernamePasswordAuthenticationToken๋ก ๋ง๋ค์ด์ ํ ํฐ์ ๋ฐ๊ธํจ 

- ๋ก๊ทธ์์์ ์งํํ  ๋ Refresh Token Entity์ doesn't have defaul value ์๋ฌ ์์ฑ
=>Redis๋ฅผ ์ด์ฉํด ๋ก๊ทธ์์ ๊ตฌํ. ํ์ง๋ง ๊ทธ๋ฅ DB๋ฅผ ์ ๋ฆฌํด์ฃผ๋ ๊ฒ์ผ๋ก ํด๊ฒฐ ๊ฐ๋ฅ.

## ๐จ API ์ค๊ณ 
https://powerful-iguana-95d.notion.site/02b032f53264463ba47ae5b6e1e2431e?v=4c2d2cdb2cfd4a94b74eb4ed69927724
