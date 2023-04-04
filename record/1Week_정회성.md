# 1Week_정회성.md

## Title: [1Week] 정회성

### 미션 요구사항 분석 & 체크리스트

---

- [x] LikeablePersonService에 삭제하는 기능 추가
- [x] LikeablePersonController에 ```likeablePerson/delete/{id}``` URL을 처리하기 위한 기능 추가
  - [x] 소유권이 본인인지 확인
    - [x] 로그인한 사람과 호감을 등록한 사람이 동일한 사람인지 확인
  - [x] 본인이 맞으면 해당 항목 삭제
- [x] 삭제 후 다시 호감목록 페이지로 돌아와야 한다.
  - [x] rq.redirectWithMsg 함수를 사용하여 ```/likeablePerson/list``` 로 돌아오기

### N주차 미션 요약

---

**[접근 방법]**



**[특이사항]**
