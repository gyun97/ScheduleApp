# API 명세서

## 일정

| 기능    | Method | URL            | request       | response        | 상태 코드      |
|-------|--------|----------------|---------------|-----------------|------------|
| 일정 등록 | POST   | /schedules     | request body  | 등록 정보           | 200: 정상 등록 |
| 일정 조회 | GET    | /schedule/{id} | request param | 단건 응답 정보        | 200: 정상 조회 |
| 일정 목록 조회 | GET    | /schedules     | request body | 다건 응답 정보        | 200: 정상 조회 |
| 일정 수정 | PUT    | /schedule/{id} | request body  | 수정 정보           | 200: 정상 수정 |
| 일정 삭제 | DELETE | /schedule/{id} | request param | --------------- | 200: 정상 삭제 |

<br>
<br>

## 담당자

| 기능    | Method | URL            | request       | response        | 상태 코드      |
|-------|--------|----------------|---------------|-----------------|------------|
| 담당자 등록 | POST   | /directors     | request body  | 등록 정보           | 200: 정상 등록 |
| 담당자 조회 | GET    | /direcotrs/{id} | request param | 단건 응답 정보        | 200: 정상 조회 |

(수정, 추가는 시간부족으로 미구현)

<br>
<br>

## 페이지
| 기능    | Method | URL            | request       | response        | 상태 코드      |
|-------|--------|----------------|---------------|-----------------|------------|
| 페이지 조회| GET   | /pages     | request body  | 등록 정보           | 200: 정상 등록 |

<br>
<br>



<br>


# ERD
![image](https://github.com/user-attachments/assets/be403973-1aa6-4e34-abe5-68fa8c640a2c)



