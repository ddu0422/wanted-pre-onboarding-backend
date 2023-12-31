# 환경설정
- 환경설정은 Mac 기준으로 작성되었습니다.

```bash
# docker가 설치되어 있지 않은 경우 docker를 설치해주세요.
brew install --cask docker
```

```bash
docker rm -f $(docker ps -qa)
docker compose -f environment/docker-compose.yaml up -d
docker ps
```

# 사용기술
|  Framework   |     Spring Boot      | 
|:------------:|:--------------------:|
| **Language** |       **Java**       |
|   **ORM**    | **Spring Data JPA**  |
|    **DB**    |    **H2 / MySQL**    |
 |   **Test**   | **JUnit5 / Mockito** |

# 기타사항
- Coding Convention은 [Google Coding Convention](https://google.github.io/styleguide/javaguide.html)을 준수합니다.
- Git Commit Convention은 아래 사항을 참고합니다.
  - prefix는 소문자로 작성합니다. 
  - prefix 이후에는 영어 / 한글로 메시지를 추가 작성합니다.
  - ex1) feat: 채용공고 등록
  - ex2) feat: add recruitment

|  Prefix  | Description                                                   |
|:--------:|:--------------------------------------------------------------
|   feat   | 새로운 기능 추가                                                     |
|   test   | Test Code 추가 및 변경<br/>(새로운 기능을 추가할 때 보통 테스트를 추가하므로 사용할 일이 적음) |
|  chore   | Production Code 변경 없는 작업                                      |
| refactor | Production Code 리팩토링                                          |
|   docs   | 문서 추가 및 변경                                                    |


# 요구사항
1. 채용공고 등록

- [x] 회사는 채용공고를 등록할 수 있다.

2. 채용공고 수정
- [x] 회사는 채용공고를 수정할 수 있다.
  - [x] 채용포지션을 수정할 수 있다.
  - [x] 채용보상금을 수정할 수 있다.
  - [x] 채용내용을 수정할 수 있다.
  - [x] 사용기술을 수정할 수 있다.

3. 채용공고 삭제
- [x] 회사는 채용공고를 삭제할 수 있다.
  - [x] 채용공고가 없는 경우 채용공고를 삭제할 수 없다. 
  - [x] 이미 삭제된 경우 채용공고를 삭제할 수 없다.
  - [x] 채용공고를 등록한 회사가 아닌 경우 채용공고를 삭제할 수 없다.

4. 채용공고 목록
- [x] 사용자는 채용공고 목록을 확인할 수 있다.
  - [x] 채용공고가 없는 경우 채용공고 비어있는 목록을 확인할 수 있다. 
  - [x] 전체 채용공고 목록을 확인할 수 있다.
  - [x] 회사명 / 채용포지션 / 사용기술에 검색어가 포함된 채용공고 목록을 확인할 수 있다.

5. 채용공고 상세페이지
- [x] 사용자는 채용공고 상세페이지를 확인할 수 있다.
  - [x] 특정 회사의 채용공고 상세페이지를 가져온다.
  - [x] 특정 회사의 다른 채용공고 목록을 가져온다.

6. 채용공고 지원
- [x] 사용자는 채용공고에 지원할 수 있다.
  - [x] 사용자는 지원하지 않은 채용공고에 지원할 수 있다.
  - [x] 사용자는 이미 지원한 채용공고에 더 이상 지원할 수 없다.
