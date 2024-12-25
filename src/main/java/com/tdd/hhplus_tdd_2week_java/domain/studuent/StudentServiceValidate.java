package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import org.springframework.util.StringUtils;

import static com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS.NOT_ENOUGH_FIELD;

public interface StudentServiceValidate {

    void isConditionAssign(StudentParam conditionParam);

    String isConditionFieldNotNull(String fieldValue);
}
