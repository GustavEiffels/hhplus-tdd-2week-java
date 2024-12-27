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
        return status.name();
    }
}
