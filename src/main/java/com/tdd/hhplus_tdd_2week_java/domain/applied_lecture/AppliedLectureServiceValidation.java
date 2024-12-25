package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;

import java.time.LocalDate;

public interface AppliedLectureServiceValidation {

    void isConditionAssign(AppliedLectureParam appliedLectureParam);


    Student isConditionFieldNotNull(Student student);

    Lecture isConditionFieldNotNull(Lecture Lecture);

    LocalDate isConditionFieldNotNull(LocalDate localDate);

    Integer isConditionFieldNotNull(Integer integer);





}
