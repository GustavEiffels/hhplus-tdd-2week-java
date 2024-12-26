package com.tdd.hhplus_tdd_2week_java.common.custom_exceptions;


import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS;
import lombok.Getter;

@Getter
public class StudentSettingException extends DomainSettingException{

    private STUDENT_STATUS status;

    public StudentSettingException(STUDENT_STATUS status, String fieldName){
        super(convertMessage(status,fieldName));
        this.status = status;
    }

    public StudentSettingException(STUDENT_STATUS status){
        super(convertMessage(status));
        this.status = status;
    }


    private static String convertMessage(STUDENT_STATUS status){
        return convertMessage(status,"");
    }

    private static String convertMessage(STUDENT_STATUS status,String fieldName){
        return status.name();
    }
}
