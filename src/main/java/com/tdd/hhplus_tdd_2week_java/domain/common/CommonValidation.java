package com.tdd.hhplus_tdd_2week_java.domain.common;


import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import static com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS.NOT_ENOUGH_FIELD;

@Component
public class CommonValidation {
    /**
     * 필드 중 StringType 이 null 인지 확인
     * @param stringFieldVal
     * @return
     */
    public String isConditionFieldNotNull(String stringFieldVal){
        if(!StringUtils.hasText(stringFieldVal)){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return stringFieldVal;
    }

    /**
     * 필드 중 Integer 타입 이 null 인지 확인
     * @param intFieldVal
     * @return
     */
    public Integer isConditionFieldNotNull(Integer intFieldVal){
        if(intFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return intFieldVal;
    }

    /**
     * LocalDate 필드가 null 인지 확인
     * @param localDateFieldVal
     * @return
     */
    public LocalDate isConditionFieldNotNull(LocalDate localDateFieldVal){
        if(localDateFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return localDateFieldVal;
    }

    /**
     * Boolean 필드가 null 인지 확인
     * @param boolFieldVal
     * @return
     */
    public Boolean isConditionFieldNotNull(Boolean boolFieldVal){
        if(boolFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return boolFieldVal;
    }

    /**
     * LONG 필드가 null 인지 확인
     * @param longFieldVal
     * @return
     */
    public Long isConditionFieldNotNull(Long longFieldVal){
        if(longFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return longFieldVal;
    }
}
