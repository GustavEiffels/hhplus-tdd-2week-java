package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;

public interface AppliedLectureServiceValidation {

    

    AppliedLectureResult convertToDto(AppliedLecture appliedLecture);

    AppliedLecture convertToEntity(AppliedLectureParam appliedLectureParam);
}
