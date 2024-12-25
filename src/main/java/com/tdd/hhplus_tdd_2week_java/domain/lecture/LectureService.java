package com.tdd.hhplus_tdd_2week_java.domain.lecture;

import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;

import java.util.List;
import java.util.Optional;

public interface LectureService {
    LectureResult           create(LectureParam lecture);

    LectureResult           update(LectureParam findParam,LectureParam updateParam);

    List<LectureResult>     readAllByCondition(LectureParam param);


    Optional<LectureResult> readByCondition(LectureParam param);


    Lecture       convertToEntity(LectureParam lectureParam);
    LectureResult convertToDto(Lecture lecture);

}
