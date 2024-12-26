package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;

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

    List<AppliedLecture> readAllWithEntityLock(AppliedLectureParam condition);

    // READ ALL
    List<AppliedLectureResult> readAllWithResult(AppliedLectureParam condition);


    Optional<AppliedLecture> isExistAppliedLecture(AppliedLectureParam appliedLectureParam);

    Optional<AppliedLecture> isExistAppliedLectureWithLock(AppliedLectureParam appliedLectureParam);


    AppliedLectureResult            convertToDto(AppliedLecture appliedLecture);

    AppliedLecture                  convertToEntity(AppliedLectureParam appliedLectureParam);
}
