package com.tdd.hhplus_tdd_2week_java.infrastructure.lecture;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tdd.hhplus_tdd_2week_java.domain.lecture.QLecture.lecture;
import static org.springframework.util.StringUtils.*;

@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {
    private final JPAQueryFactory dsl;


    @Override
    public Optional<Lecture> findByCondition(LectureParam param) {
        return Optional.ofNullable(
                dsl.selectFrom(lecture)
                        .where(
                                idEq(param.getId()),
                                nameEq(param.getName()),
                                instructorNameEq(param.getInstructorName()),
                                locationEq(param.getLocation()),
                                lectureDateEq(param.getLectureDate()),
                                startTimeEq(param.getStartTime()),
                                endTimeEq(param.getEndTime()),
                                isEnrollmentOpenEq(param.getIsEnrollmentOpen())
                        )
                        .fetchOne());
    }

    @Override
    public Optional<LectureResult> findByConditionWithResult(LectureParam param) {
        return Optional.ofNullable(
                dsl.select(
                                Projections.bean(
                                        LectureResult.class
                                        ,lecture.id.as("id")
                                        ,lecture.name.as("name")
                                        ,lecture.instructorName.as("instructorName")
                                        ,lecture.location.as("location")
                                        ,lecture.lectureDate.as("lectureDate")
                                        ,lecture.dayInfo.as("dayInfo")
                                        ,lecture.startTime.as("startTime")
                                        ,lecture.endTime.as("endTime")
                                        ,lecture.isEnrollmentOpen.as("isEnrollmentOpen")
                                )).from(lecture)
                        .where(
                                idEq(param.getId()),
                                nameEq(param.getName()),
                                instructorNameEq(param.getInstructorName()),
                                locationEq(param.getLocation()),
                                lectureDateEq(param.getLectureDate()),
                                startTimeEq(param.getStartTime()),
                                endTimeEq(param.getEndTime()),
                                isEnrollmentOpenEq(param.getIsEnrollmentOpen())
                        )
                        .fetchOne());
    }

    @Override
    public List<Lecture> findAllByCondition(LectureParam param) {
        return dsl.selectFrom(lecture)
                .where(
                        idEq(param.getId()),
                        nameEq(param.getName()),
                        instructorNameEq(param.getInstructorName()),
                        locationEq(param.getLocation()),
                        lectureDateEq(param.getLectureDate()),
                        startTimeEq(param.getStartTime()),
                        endTimeEq(param.getEndTime()),
                        isEnrollmentOpenEq(param.getIsEnrollmentOpen())
                )
                .fetch();
    }

    @Override
    public List<LectureResult> findAllByConditionWithResult(LectureParam param) {
        return dsl.select(
                        Projections.bean(
                                LectureResult.class
                                ,lecture.id.as("id")
                                ,lecture.name.as("name")
                                ,lecture.instructorName.as("instructorName")
                                ,lecture.location.as("location")
                                ,lecture.lectureDate.as("lectureDate")
                                ,lecture.dayInfo.as("dayInfo")
                                ,lecture.startTime.as("startTime")
                                ,lecture.endTime.as("endTime")
                                ,lecture.isEnrollmentOpen.as("isEnrollmentOpen")
                        )).from(lecture)
                .where(
                        idEq(param.getId()),
                        nameEq(param.getName()),
                        instructorNameEq(param.getInstructorName()),
                        locationEq(param.getLocation()),
                        lectureDateEq(param.getLectureDate()),
                        startTimeEq(param.getStartTime()),
                        endTimeEq(param.getEndTime()),
                        isEnrollmentOpenEq(param.getIsEnrollmentOpen())
                )
                .fetch();
    }


    private BooleanExpression idEq(Long id){
        return id != null ? lecture.id.eq(id):null;
    }

    private BooleanExpression nameEq(String lectureName){
        return hasText(lectureName) ? lecture.name.eq(lectureName):null;
    }

    private BooleanExpression instructorNameEq(String instructorName){
        return hasText(instructorName) ? lecture.instructorName.eq(instructorName):null;
    }

    private BooleanExpression locationEq(String location){
        return hasText(location) ? lecture.location.eq(location):null;
    }

    private BooleanExpression lectureDateEq(LocalDate lectureDate){
        return lectureDate != null ? lecture.lectureDate.eq(lectureDate):null;
    }

    private BooleanExpression startTimeEq(Integer startTime){
        return startTime != null ? lecture.startTime.eq(startTime):null;
    }

    private BooleanExpression endTimeEq(Integer endTime){
        return endTime != null ? lecture.endTime.eq(endTime):null;
    }

    private BooleanExpression isEnrollmentOpenEq(Boolean isEnrollmentOpen){
        return isEnrollmentOpen != null? lecture.isEnrollmentOpen.eq(isEnrollmentOpen):null;
    }



}
