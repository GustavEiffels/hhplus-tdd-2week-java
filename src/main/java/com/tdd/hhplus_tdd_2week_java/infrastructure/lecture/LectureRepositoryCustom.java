package com.tdd.hhplus_tdd_2week_java.infrastructure.lecture;


import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;

import java.util.List;
import java.util.Optional;

public interface LectureRepositoryCustom {

    /**
     * PESSIMISTIC
     */
    Optional<Lecture> findByIdWithLock(Long lectureId);

    /**
     * 단건 조회
     * - entity 반환
     * @param param
     * @return
     */
    Optional<Lecture> findByCondition(LectureParam param);

    /**
     * 단건 조회
     * - result 반환
     * @param param
     * @return
     */
    Optional<LectureResult> findByConditionWithResult(LectureParam param);

    /**
     * 다건 조회
     * - entity 리스트 반환
     * @param param
     * @return
     */
    List<Lecture> findAllByCondition(LectureParam param);

    /**
     * 다건 조회
     * - result 리스트 반환
     * @param param
     * @return
     */
    List<LectureResult> findAllByConditionWithResult(LectureParam param);


}
