package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.APPLIED_LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureServiceValidation;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.APPLIED_LECTURE_STATUS.NOT_ENOUGH_ASSIGN;

@Component
public class AppliedLectureServiceValidationImpl implements AppliedLectureServiceValidation {
    @Override
    public void isConditionAssign(AppliedLectureParam appliedLectureParam) {
        if(
                appliedLectureParam.getLecture() == null
                        && appliedLectureParam.getStudent() == null
                        && appliedLectureParam.getId() == null
                        && appliedLectureParam.getLectureDate() == null
                        && appliedLectureParam.getStartTime() == null
                        && appliedLectureParam.getEndTime() == null ){
            throw new AppliedLectureSettingException(NOT_ENOUGH_ASSIGN);
        }
    }

    @Override
    public Student isConditionFieldNotNull(Student student) {
        if(student == null){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.NOT_ENOUGH_ASSIGN);
        }
        return student;
    }

    @Override
    public Lecture isConditionFieldNotNull(Lecture Lecture) {
        if(Lecture == null){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.NOT_ENOUGH_ASSIGN);
        }
        return Lecture;
    }

    @Override
    public LocalDate isConditionFieldNotNull(LocalDate localDate) {
        if(localDate == null){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.NOT_ENOUGH_ASSIGN);
        }
        return localDate;
    }

    @Override
    public Integer isConditionFieldNotNull(Integer integer) {
        if(integer == null){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.NOT_ENOUGH_ASSIGN);
        }
        return integer;
    }

}
