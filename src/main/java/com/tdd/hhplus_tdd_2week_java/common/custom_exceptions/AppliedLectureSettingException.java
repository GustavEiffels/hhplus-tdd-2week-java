package com.tdd.hhplus_tdd_2week_java.common.custom_exceptions;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.APPLIED_LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import lombok.Getter;

@Getter
public class AppliedLectureSettingException extends DomainSettingException{

    private APPLIED_LECTURE_STATUS status;

    public AppliedLectureSettingException(APPLIED_LECTURE_STATUS status) {
        super(convertMessage(status));
        this.status = status;
    }
    private static String convertMessage(APPLIED_LECTURE_STATUS status){
        return "TEST";
    }


}
