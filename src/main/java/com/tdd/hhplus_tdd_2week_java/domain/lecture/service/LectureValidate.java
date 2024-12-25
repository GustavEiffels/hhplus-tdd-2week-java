package com.tdd.hhplus_tdd_2week_java.domain.lecture.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import static com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS.LECTURE_CONFLICT;
import static com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS.NOT_ENOUGH_FIELD;

@Component
public class LectureValidate {

    public void isLectureConflict(int oldStart, int oldEnd, int newStart, int newEnd){
        if( newStart <= oldStart && newEnd > oldStart){
            throw new LectureSettingException(LECTURE_CONFLICT);
        }
        if( newStart < oldEnd && newEnd > oldEnd ){
            throw new LectureSettingException(LECTURE_CONFLICT);
        }
    }

    public void isConditionAssign(LectureParam lectureParam){
        if(lectureParam.getId() == null &&
                lectureParam.getName() == null &&
                lectureParam.getInstructorName() == null &&
                lectureParam.getLocation() == null &&
                lectureParam.getLectureDate() == null &&
                lectureParam.getIsEnrollmentOpen() == null &&
                lectureParam.getStartTime() == null &&
                lectureParam.getEndTime() == null){
            throw new RuntimeException("NOT ENOUGH DTO");
        }
    }

    public String isConditionFieldNotNull(String stringFieldVal){
        if(!StringUtils.hasText(stringFieldVal)){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return stringFieldVal;
    }

    public Integer isConditionFieldNotNull(Integer intFieldVal){
        if(intFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return intFieldVal;
    }

    public LocalDate isConditionFieldNotNull(LocalDate localDateFieldVal){
        if(localDateFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return localDateFieldVal;
    }

    public Boolean isConditionFieldNotNull(Boolean boolFieldVal){
        if(boolFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return boolFieldVal;
    }
}
