package com.tdd.hhplus_tdd_2week_java.infrastructure.lecture;


import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;

import java.util.List;
import java.util.Optional;

public interface LectureRepositoryCustom {

    Optional<Lecture> findByCondition(LectureParam param);


    List<LectureResult> findAllByCondition(LectureParam param);

}
