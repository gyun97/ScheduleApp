# API 명세서

| 기능    | Method | URL            | request       | response        | 상태 코드      |
|-------|--------|----------------|---------------|-----------------|------------|
| 일정 등록 | POST   | /schedules     | request body  | 등록 정보           | 200: 정상 등록 |
| 일정 조회 | GET    | /schedule/{id} | request param | 단건 응답 정보        | 200: 정상 조회 |
| 일정 목록 조회 | GET    | /schedules     | request param | 다건 응답 정보        | 200: 정상 조회 |
| 일정 수정 | PUT    | /schedule/{id} | request body  | 수정 정보           | 200: 정상 수정 |
| 일정 삭제 | DELETE | /schedule/{id} | request param | --------------- | 200: 정상 삭제 |

<br>
<br>
<br>


# ERD
![image](https://github.com/user-attachments/assets/d9f19ab2-18be-4d77-8463-a65b5043321e)


