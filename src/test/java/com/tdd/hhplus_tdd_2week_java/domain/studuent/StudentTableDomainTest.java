package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTableDomainTest {
    private String testStringGenerator(int length){
        StringBuilder sb = new StringBuilder();
        for(int i  = 0; i<length; i++){
            sb.append("t");
        }
        return sb.toString();
    }

    // name
    /**
     * 학생 이름 : 2 ~ 50 글자 사이 한글 영문만 가능
     */
    private void name_exceptionMatcher(String name, STUDENT_STATUS status){
        StudentSettingException exception = assertThrows(StudentSettingException.class,()->{
            new Student().isValid_Name(name);
        });
        assertEquals(status,exception.getStatus());
    }

    @Test
    void name_exceptionCase(){
        String empty = "";
        String val_null = null;
        String val_char_1 = "가";
        String val_char_51 = testStringGenerator(51);
        String val_name_add_int = "김연습1";
        String val_name_add_special = "김연습!";
        name_exceptionMatcher(empty,STUDENT_STATUS.INVALID_STUDENT_NAME);
        name_exceptionMatcher(val_null,STUDENT_STATUS.VALUE_IS_NULL);
        name_exceptionMatcher(val_char_1,STUDENT_STATUS.INVALID_STUDENT_NAME);
        name_exceptionMatcher(val_char_51,STUDENT_STATUS.INVALID_STUDENT_NAME);
        name_exceptionMatcher(val_name_add_int,STUDENT_STATUS.INVALID_STUDENT_NAME);
        name_exceptionMatcher(val_name_add_special,STUDENT_STATUS.INVALID_STUDENT_NAME);
        new Student().isValid_Name("김연습이");
    }

    /**
     * 학생 코드 : studentCode
     * --> exception
     * 빈값
     * null 값
     * 7 가 아닌 경우
     * 연도가 2020 미만 현재년도 초과
     * 학과 01 - 09 가 아닌경우
     * 번호 0이하 100초과
     */
    private void studentCode_exceptionMatcher(String studentCode, STUDENT_STATUS status){
        StudentSettingException exception = assertThrows(StudentSettingException.class,()->{
            new Student().isValid_StudentCode(studentCode);
        });
        assertEquals(status,exception.getStatus());
    }

    @Test
    void studentCode_exceptionCases(){
        String code_empty       = "";
        String code_null        = null;
        String code_not_digit   = testStringGenerator(9);
        String code_digit_8     = "20200101";
        String code_year_2019   = "201909001";
        String code_year_2099   = "209909001";
        String code_major_11    = "202011001";
        String code_number_000  = "202001000";
        String code_number_101  = "202001101";

        studentCode_exceptionMatcher(code_empty,STUDENT_STATUS.INVALID_STUDENT_CODE);
        studentCode_exceptionMatcher(code_null,STUDENT_STATUS.VALUE_IS_NULL);
        studentCode_exceptionMatcher(code_not_digit,STUDENT_STATUS.INVALID_STUDENT_CODE);
        studentCode_exceptionMatcher(code_digit_8,STUDENT_STATUS.INVALID_STUDENT_CODE);
        studentCode_exceptionMatcher(code_year_2019,STUDENT_STATUS.INVALID_STUDENT_CODE_YEAR);
        studentCode_exceptionMatcher(code_year_2099,STUDENT_STATUS.INVALID_STUDENT_CODE_YEAR);
        studentCode_exceptionMatcher(code_major_11,STUDENT_STATUS.NOT_EXIST_MAJOR);
        studentCode_exceptionMatcher(code_number_000,STUDENT_STATUS.INVALID_STUDENT_NUMBER);
        studentCode_exceptionMatcher(code_number_101,STUDENT_STATUS.INVALID_STUDENT_NUMBER);

        new Student().isValid_StudentCode("202308011");
    }


}