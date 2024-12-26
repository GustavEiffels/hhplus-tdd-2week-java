package com.tdd.hhplus_tdd_2week_java.domain.lecture;

import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;

import java.util.List;
import java.util.Optional;

public interface LectureService {

    // CREATE
    LectureResult create(LectureParam lectureParam);


    // UPDATE
    LectureResult updateWithResult(Lecture existLecture, LectureParam updateParam);


    // UPDATE
    Lecture updateWithEntity(Lecture existLecture, LectureParam updatedLecture);

    // READ
    Optional<Lecture> readWithEntity(LectureParam condition);

    // READ
    Optional<LectureResult> readWithResult(LectureParam condition);


    // READ ALL
    List<Lecture> readAllWithEntity(LectureParam condition);

    /// READ ALL
    List<LectureResult> readAllWithResult(LectureParam condition);




    Lecture isLectureExist(Long lectureId);

    Lecture isLectureExistUseLock(Long lectureId);


    Lecture convertToEntity(LectureParam lectureParam);

    LectureResult convertToResult(Lecture lecture);

}
