package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;

public interface StudentServiceValidate {

    void isConditionAssign(StudentParam conditionParam);

    String isConditionFieldNotNull(String fieldValue);

    Long isConditionFieldNotNull(Long fieldValue);
}
