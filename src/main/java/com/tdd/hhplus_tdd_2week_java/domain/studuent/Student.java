package com.tdd.hhplus_tdd_2week_java.domain.studuent;


import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.BaseEntity;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
/**
 * policy
 * 생성
 * - 이름은 한글 또는 영어만 사용 가능하며 50자 내외
 * - 학번은 2024_01_001 이렇게 9 글자 ( 숫자만 ) 이어야 하며
 *      첫번째 4글자는  최소 2020 이며 최대는 아직 2024 까지만
 *      두번째 2글자는  01 부터 09 까지 학번을 나타냄
 *      세번째 3글자는  001 부터 100 까지 학생 수를 나타냄 101 , 000 없음
 *
 */
public class Student extends BaseEntity {

    // 모든 Entity 의 id 값을 long 으로 사용한다는 보장이 없음
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String studentCode;


    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    public List<AppliedLecture> appliedLectures = new ArrayList<>();

    public Student(String name, String studentCode){
        this.name = isValid_Name(name);
        this.studentCode = isValid_StudentCode(studentCode);
    }


    public String isValid_StringTypeFields(String fieldVal,String fieldNm,String regex, STUDENT_STATUS exception_status){
        if(fieldVal == null){
            throw new StudentSettingException(STUDENT_STATUS.VALUE_IS_NULL,fieldNm);
        }
        fieldVal = fieldVal.trim();

        if( !Pattern.compile(regex).matcher(fieldVal).matches() ){
            throw new StudentSettingException(exception_status);
        }
        return fieldVal;
    }

    public String isValid_Name(String name){
        return isValid_StringTypeFields(
                name,
                "학생이름",
                "^[a-zA-Z가-힣]{2,50}$",
                STUDENT_STATUS.INVALID_STUDENT_NAME);
    }

    public String isValid_StudentCode(String studentCode){
        if(studentCode == null){
            throw new StudentSettingException(STUDENT_STATUS.VALUE_IS_NULL,"studentCode");
        }

        if(!studentCode.matches("^[0-9]{9}$")){
            throw new StudentSettingException(STUDENT_STATUS.INVALID_STUDENT_CODE);
        }

        Integer studentCodeYear = Integer.valueOf(studentCode.substring(0,4));

        if( studentCodeYear < 2020 || LocalDate.now().getYear() < studentCodeYear ){
            throw new StudentSettingException(STUDENT_STATUS.INVALID_STUDENT_CODE_YEAR);
        }

        Integer studentMajorCode = Integer.valueOf(studentCode.substring(4,6));
        if( studentMajorCode <= 0 || studentMajorCode > 9 ){
            throw new StudentSettingException(STUDENT_STATUS.NOT_EXIST_MAJOR);
        }

        Integer studentNumber = Integer.valueOf(studentCode.substring(6));
        if( studentNumber <= 0 || studentNumber > 100 ){
            throw new StudentSettingException(STUDENT_STATUS.INVALID_STUDENT_NUMBER);
        }

        return studentCode;
    }

}
