package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;

import java.util.List;
import java.util.Optional;

public interface AppliedLectureService {


    // CREATE
    AppliedLectureResult create(AppliedLectureParam param);

    // UPDATE
    AppliedLecture      updateWithEntity(AppliedLecture exist, AppliedLectureParam updateParam);

    AppliedLectureResult updateWithResult(AppliedLecture exist, AppliedLectureParam updateParam);


    // READ
    Optional<AppliedLecture> readWithEntity(AppliedLectureParam condition);

    // READ
    Optional<AppliedLectureResult> readWithResult(AppliedLectureParam condition);

    // READ ALL
    List<AppliedLecture> readAllWithEntity(AppliedLectureParam condition);

    // READ ALL
    List<AppliedLectureResult> readAllWithResult(AppliedLectureParam condition);


    AppliedLectureResult            convertToDto(AppliedLecture appliedLecture);

    AppliedLecture                  convertToEntity(AppliedLectureParam appliedLectureParam);
}
