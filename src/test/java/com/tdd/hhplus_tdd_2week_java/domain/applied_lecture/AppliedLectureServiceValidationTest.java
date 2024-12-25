package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.service.AppliedLectureServiceValidationImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppliedLectureServiceValidationTest {

    private final AppliedLectureServiceValidation lectureServiceValidation = new AppliedLectureServiceValidationImpl();


    /**
     * isConditionAssign
     * - AppliedLectureParam 필드에 아무것도 할당이 되지 않았을 때
     */
    @Test
    void isConditionAssign_Exception(){
        AppliedLectureSettingException exception = assertThrows(AppliedLectureSettingException.class,()->{
            lectureServiceValidation.isConditionAssign(AppliedLectureParam.builder().build());
        });
        assertEquals(APPLIED_LECTURE_STATUS.NOT_ENOUGH_ASSIGN,exception.getStatus());
    }


}