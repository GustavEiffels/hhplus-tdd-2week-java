# ERD
![image](https://github.com/user-attachments/assets/c945b8ab-9f81-4788-ab24-90d6dad3c957)
### Lecture 
- 강의 정보를 저장하는 테이블 
- name : 강의 이름은 2글자 이상 50자이하의 영어 한글 숫자만 사용한 문자일 것
- instructorName : 강사 이름 한글 또는 영어이고 50자 이내여야 할것
- location : 강의실 위치 이름은 20 자리 이내 이어야 할 것
- lectureDate : 생성시 현재 날짜보다 뒤에 있어야함 ( 같거나 앞이면 안됨 )
- dayInfo : LocalDate 의 getDayofWeek 을 String 으로 변환한 값 ( day 에서 H2 DB 키 값 문제로 dayInfo 로 이름 변경 )
- isEnrollmentOpen : 강의 수강 가능한지-> default false
- startTime : 09<= 시작 시간 <= 17.
- endTime : 10<= 종료 시간 <= 18
> 강의 테이블의 경우 장소가 같고 시간이 겹치는 경우 
생성하지 못하도록 구성하려고 했습니다. 
시작시간과 종료 시간을 준 것은 일반 대학교에서 진행하는 시간을 참고하였고,
때문에 주말에는 강의를 열지 않도록 설정하였습니다. 
실제로 학교에서 사용할만한 제약조건을 추가하였습니다.

### Student
- 학생 정보를 저장하는 테이블 
- name : 학생이름 | 한글 또는 영어만 사용 가능하며 50자 내외
- studentCode  : 학번 | 2024_01_001 이렇게 9 글자 ( 숫자만 ) 이어야 하며
index 0 - 3 최소 2020 이며 최대는 아직 2024 까지만
index 4 - 5 01 부터 09 까지 학번을 나타냄
index 6 - 8 001 부터 100 까지 학생 수를 나타냄 101 , 000 없음
> 학번이라는 필드를 두어 학생 레코드의 유일성을 추가하였습니다.

### AppliedLecture
- Student 와 Lecture 테이블을 Mapping 하는 Mapping 테이블 
- lecture  : 강의 외래키 
- student : 학생 외래키
- startTime : 강의 시작시간
- endTime : 강의 종료 시간 
- lectureDateTime : 강의 날짜
> 코치님에게 문의드린 결과 읽기 효과를 극대화 하기 위해
lecture 의 일부 필드를 그대로 사용하여 JOIN 횟수를 줄이도록 구성하였습니다.




 
