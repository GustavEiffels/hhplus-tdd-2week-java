package com.tdd.hhplus_tdd_2week_java.domain.studuent.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS.NOT_ENOUGH_FIELD;

@Component
public class StudentServiceValidateImpl implements StudentServiceValidate {
    @Override
    public void isConditionAssign(StudentParam conditionParam) {
        if(
                conditionParam.getId() == null &&
        conditionParam.getStudentCode() == null &&
        conditionParam.getName() == null){
            throw new StudentSettingException(NOT_ENOUGH_FIELD);
        }
    }

    @Override
    public String isConditionFieldNotNull(String fieldValue) {
        if(!StringUtils.hasText(fieldValue)){
            throw new StudentSettingException(NOT_ENOUGH_FIELD);
        }
        return fieldValue;
    }

    @Override
    public Long isConditionFieldNotNull(Long fieldValue) {
        if(fieldValue == null){
            throw new StudentSettingException(NOT_ENOUGH_FIELD);
        }
        return fieldValue;
    }
}
