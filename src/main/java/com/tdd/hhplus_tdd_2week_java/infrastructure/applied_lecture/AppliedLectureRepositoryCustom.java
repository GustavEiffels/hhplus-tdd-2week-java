package com.tdd.hhplus_tdd_2week_java.infrastructure.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;

import java.util.List;
import java.util.Optional;

public interface AppliedLectureRepositoryCustom {

    Optional<AppliedLecture>       findByCondition(AppliedLectureParam param);

    Optional<AppliedLectureResult> findByConditionWithResult(AppliedLectureParam param);

    List<AppliedLecture>           findAllByCondition(AppliedLectureParam param);

    List<AppliedLectureResult>     findAllByConditionWithResult(AppliedLectureParam param);
}
