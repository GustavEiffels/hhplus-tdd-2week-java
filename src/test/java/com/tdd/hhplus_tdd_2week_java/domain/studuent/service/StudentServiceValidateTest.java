package com.tdd.hhplus_tdd_2week_java.domain.studuent.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceValidateTest {

    private StudentServiceValidate serviceValidate;

    /**
     * isConditionAssign
     * - exception
     *  StudentParam 의 필드가 할당되지 않으면 예외
     */
    @Test
    void isConditionAssign_exception(){
        serviceValidate = new StudentServiceValidateImpl();

        StudentSettingException exception = Assertions.assertThrows(StudentSettingException.class,()->{
            serviceValidate.isConditionAssign(StudentParam.builder().build());
        });

        Assertions.assertEquals(STUDENT_STATUS.NOT_ENOUGH_FIELD,exception.getStatus());
    }

}