package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;

import java.util.Optional;

public interface AppliedLectureService {

    AppliedLectureResult create(AppliedLectureParam param);

    AppliedLectureResult update(AppliedLectureParam findParam, AppliedLectureParam updateParam);

    Optional<AppliedLectureResult> findByCondition(AppliedLectureParam findParam);
}
