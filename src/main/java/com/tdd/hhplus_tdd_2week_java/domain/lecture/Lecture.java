package com.tdd.hhplus_tdd_2week_java.domain.lecture;


import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.BaseEntity;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
/**
 * name : 강의 이름은 2글자 이상 50자이하의 영어 한글 숫자만 사용한 문자일 것
 * instructorName   : 강사 이름 한글 또는 영어이고 50자 이내여야 할것
 * location         : 강의실 위치 이름은 20 자리 이내 이어야 할 것
 * lectureDate      : 생성시  현재 날짜보다 뒤에 있어야함 ( 같거나 앞이면 안됨 )
 * day              : mon, tue, wed,thr, fri 만 존재
 * isEnrollmentOpen : 강의 수강 가능한지-> default false
 * startTime        : 09<= 시작 시간 <= 17
 * endTime          : 10<= 종료 시간 <= 18
 */
public class Lecture extends BaseEntity {

    // 모든 Entity 의 id 값을 long 으로 사용한다는 보장이 없음
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String    name              ; // 강의 이름
    @Column(nullable = false)
    private String    instructorName    ;

    @Column(nullable = false)
    private String    location          ;

    @Column(nullable = false)
    private LocalDate lectureDate       ;

    @Column(nullable = false)
    private String    day               ;

    @Column(nullable = false)
    private Boolean   isEnrollmentOpen  ;

    @Column(nullable = false)
    private int       startTime         ;

    @Column(nullable = false)
    private int       endTime           ;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    public List<AppliedLecture> appliedLectures = new ArrayList<>();

    public Lecture(
            String name,
            String instructorName,
            String location,
            LocalDate lectureDate,
            int startTime,
            int endTime) {

        this.name               = isValid_LectureNm(name);
        this.instructorName     = isValid_InstructorName(instructorName);
        this.location           = isValid_Location(location);
        this.lectureDate        = isValid_LectureDate(lectureDate);
        this.day                = lectureDate.getDayOfWeek().toString();
        this.isEnrollmentOpen   = true;
        this.startTime          = isValid_StartTime(startTime);
        this.endTime            = isValid_EndTime(endTime);
        isValid_RunningTime(startTime,endTime);
    }

    public String isValid_StringTypeFields(String fieldVal,String fieldNm,String regex, LECTURE_STATUS exception_status){
        if(fieldVal == null){
            throw new LectureSettingException(LECTURE_STATUS.VALUE_IS_NULL,fieldNm);
        }
        fieldVal = fieldVal.trim();

        if( !Pattern.compile(regex).matcher(fieldVal).matches() ){
            throw new LectureSettingException(exception_status);
        }
        return fieldVal;
    }

    public String isValid_InstructorName(String instructorName) {
        return isValid_StringTypeFields(
                instructorName,
                "강사 이름",
                "^[a-zA-Z가-힣]{2,50}$",
                LECTURE_STATUS.INVALID_INSTRUCTOR_NAME);
    }
    public String isValid_LectureNm(String lectureNm) {
        return isValid_StringTypeFields(
                lectureNm,
                "강의 이름",
                "^[a-zA-Z가-힣0-9\\s]{2,50}$",
                LECTURE_STATUS.INVALID_LECTURE_NAME);
    }

    public String isValid_Location(String location) {
        return isValid_StringTypeFields(
                location,
                "강사 이름",
                "^[a-zA-Z가-힣0-9\\s]{2,20}$",
                LECTURE_STATUS.INVALID_LOCATION_NAME);
    }

    public LocalDate isValid_LectureDate(LocalDate lectureDate){
        if(lectureDate == null){
            throw new LectureSettingException(LECTURE_STATUS.VALUE_IS_NULL,"강의 날짜");
        }
        if(!lectureDate.isAfter(LocalDate.now())) {
            throw new LectureSettingException(LECTURE_STATUS.INVALID_LECTURE_DATE);
        }
        if (lectureDate.getDayOfWeek() == DayOfWeek.SUNDAY || lectureDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            throw new LectureSettingException(LECTURE_STATUS.INVALID_LECTURE_DATE);
        }

        return  lectureDate;
    }

    public int isValid_StartTime(int startTime){
        if( 9>startTime || 17<startTime){
            throw new LectureSettingException(LECTURE_STATUS.INVALID_START_TIME);
        }
        return startTime;
    }

    public int isValid_EndTime(int endTime){
        if( 10>endTime || 18<endTime){
            throw new LectureSettingException(LECTURE_STATUS.INVALID_END_TIME);
        }
        return endTime;
    }

    public void isValid_RunningTime(int startTime, int endTime){
        if( startTime >= endTime || ( endTime-startTime >3 ) ){
            throw new LectureSettingException(LECTURE_STATUS.INVALID_RUNNING_TIME);
        }
    }


}
