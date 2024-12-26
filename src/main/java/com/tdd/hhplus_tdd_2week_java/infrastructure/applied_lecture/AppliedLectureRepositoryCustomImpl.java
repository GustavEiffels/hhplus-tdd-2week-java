package com.tdd.hhplus_tdd_2week_java.infrastructure.applied_lecture;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.QAppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.infrastructure.lecture.LectureRepositoryCustom;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.QAppliedLecture.appliedLecture;

@RequiredArgsConstructor
public class AppliedLectureRepositoryCustomImpl implements AppliedLectureRepositoryCustom {
    private final JPAQueryFactory dsl;


    @Override
    public Optional<AppliedLecture> findByIdWithLock(AppliedLectureParam param) {
        return Optional.ofNullable(
                dsl.selectFrom(appliedLecture)
                        .where(
                                idEq(param.getId())
                                ,studentEq(param.getStudent())
                                ,lectureEq(param.getLecture())
                        ).setLockMode(LockModeType.PESSIMISTIC_WRITE).fetchOne());
    }

    @Override
    public Optional<AppliedLecture> findByCondition(AppliedLectureParam param) {
        return Optional.ofNullable(
                dsl.selectFrom(appliedLecture)
                .where(
                        idEq(param.getId())
                        ,studentEq(param.getStudent())
                        ,lectureEq(param.getLecture())
                        ,lectureDateEq(param.getLectureDate())
                        ,startTimeEq(param.getStartTime())
                        ,endTimeEq(param.getEndTime())
                ).fetchOne());
    }

    @Override
    public Optional<AppliedLectureResult> findByConditionWithResult(AppliedLectureParam param) {
        return Optional.ofNullable(
                dsl.select(
                        Projections.bean(
                                AppliedLectureResult.class
                                , appliedLecture.id.as("id")
                                , appliedLecture.student.as("student")
                                , appliedLecture.lecture.as("lecture")
                                , appliedLecture.lectureDate.as("lectureDate")
                                , appliedLecture.startTime.as("startTime")
                                , appliedLecture.endTime.as("endTime")
                        )
                ).from(appliedLecture)
                                .where(
                                        idEq(param.getId())
                                        ,studentEq(param.getStudent())
                                        ,lectureEq(param.getLecture())
                                        ,lectureDateEq(param.getLectureDate())
                                        ,startTimeEq(param.getStartTime())
                                        ,endTimeEq(param.getEndTime())
                        ).fetchOne());
    }

    @Override
    public List<AppliedLectureResult> findAllByConditionWithResult(AppliedLectureParam param) {
        return dsl.select(
                Projections.bean(
                        AppliedLectureResult.class
                        , appliedLecture.id.as("id")
                        , appliedLecture.student.as("student")
                        , appliedLecture.lecture.as("lecture")
                        , appliedLecture.lectureDate.as("lectureDate")
                        , appliedLecture.startTime.as("startTime")
                        , appliedLecture.endTime.as("endTime")
                )
        ).from(appliedLecture)
                .where(
                         idEq(param.getId())
                        ,studentEq(param.getStudent())
                        ,lectureEq(param.getLecture())
                        ,lectureDateEq(param.getLectureDate())
                        ,startTimeEq(param.getStartTime())
                        ,endTimeEq(param.getEndTime())
                ).fetch();
    }

    @Override
    public List<AppliedLecture> findAllByCondition(AppliedLectureParam param) {
        return dsl.selectFrom(appliedLecture)
                .where(
                        idEq(param.getId())
                        ,studentEq(param.getStudent())
                        ,lectureEq(param.getLecture())
                        ,lectureDateEq(param.getLectureDate())
                        ,startTimeEq(param.getStartTime())
                        ,endTimeEq(param.getEndTime())
                ).fetch();
    }

    @Override
    public List<AppliedLecture> findAllByConditionLock(AppliedLectureParam param) {
        return dsl.selectFrom(appliedLecture)
                .where(
                        idEq(param.getId())
                        ,studentEq(param.getStudent())
                        ,lectureEq(param.getLecture())
                        ,lectureDateEq(param.getLectureDate())
                        ,startTimeEq(param.getStartTime())
                        ,endTimeEq(param.getEndTime())
                ).setLockMode(LockModeType.PESSIMISTIC_READ).fetch();
    }


    private BooleanExpression idEq(Long id){
        return id != null ? appliedLecture.id.eq(id):null;
    }

    private BooleanExpression studentEq(Student student){
        return student != null ? appliedLecture.student.eq(student):null;
    }

    private BooleanExpression lectureEq(Lecture lecture){
        return lecture != null ? appliedLecture.lecture.eq(lecture):null;
    }

    private BooleanExpression lectureDateEq(LocalDate lectureDate){
        return lectureDate != null ? appliedLecture.lectureDate.eq(lectureDate):null;
    }

    private BooleanExpression startTimeEq(Integer startTime){
        return startTime != null ? appliedLecture.startTime.eq(startTime):null;
    }

    private BooleanExpression endTimeEq(Integer endTime){
        return endTime != null ? appliedLecture.endTime.eq(endTime):null;
    }
}
