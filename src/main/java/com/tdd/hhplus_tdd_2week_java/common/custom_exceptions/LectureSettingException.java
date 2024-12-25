package com.tdd.hhplus_tdd_2week_java.common.custom_exceptions;


import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import lombok.Getter;

@Getter
public class LectureSettingException extends DomainSettingException{

    private LECTURE_STATUS status;

    public LectureSettingException(LECTURE_STATUS status,String fieldName){
        super(convertMessage(status,fieldName));
        this.status = status;
    }

    public LectureSettingException(LECTURE_STATUS status){
        super(convertMessage(status));
        this.status = status;
    }


    private static String convertMessage(LECTURE_STATUS status){
        return convertMessage(status,"");
    }

    private static String convertMessage(LECTURE_STATUS status,String fieldName){

        if(status.equals(LECTURE_STATUS.VALUE_IS_NULL)){
            return "["+fieldName+"] 값을 받지 않았습니다.";
        }
        if(status.equals(LECTURE_STATUS.INVALID_INSTRUCTOR_NAME)){
            return "강사 이름 한글 또는 영어이고 50자 이내여야 할것";
        }
        return null;
    }
}
