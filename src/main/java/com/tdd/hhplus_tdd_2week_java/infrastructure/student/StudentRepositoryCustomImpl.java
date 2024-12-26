package com.tdd.hhplus_tdd_2week_java.infrastructure.student;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.QAppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.QLecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.QAppliedLecture.appliedLecture;
import static com.tdd.hhplus_tdd_2week_java.domain.lecture.QLecture.lecture;
import static com.tdd.hhplus_tdd_2week_java.domain.studuent.QStudent.student;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom{

    private final JPAQueryFactory dsl;

    @Override
    public Optional<Student> findByCondition(StudentParam condition) {
        return Optional.ofNullable(
            dsl.selectFrom(student)
                    .where(
                            idEq(condition.getId()),
                            nameEq(condition.getName()),
                            studentCodeEq(condition.getStudentCode()))
                    .fetchOne()
        );
    }

    @Override
    public Optional<StudentResult> findByConditionWithResult(StudentParam condition) {
        return Optional.ofNullable(
                dsl.select(
                                Projections.bean(
                                        StudentResult.class,
                                         student.id.as("id")
                                        ,student.name.as("name")
                                        ,student.studentCode.as("studentCode")
                                )
                        )
                        .from(student)
                        .where(
                                idEq(condition.getId()),
                                nameEq(condition.getName()),
                                studentCodeEq(condition.getStudentCode()))
                        .fetchOne()
        );
    }

    @Override
    public List<Student> findAllByCondition(StudentParam condition) {
        return dsl.selectFrom(student)
                .where(
                        idEq(condition.getId()),
                        nameEq(condition.getName()),
                        studentCodeEq(condition.getStudentCode())).fetch();
    }

    @Override
    public List<StudentResult> findAllByConditionWithResult(StudentParam condition) {
        return
                dsl.select(
                                Projections.bean(
                                        StudentResult.class,
                                        student.id.as("id")
                                        ,student.name.as("name")
                                        ,student.studentCode.as("studentCode")
                                )
                        )
                        .from(student)
                .where(
                        idEq(condition.getId()),
                        nameEq(condition.getName()),
                        studentCodeEq(condition.getStudentCode())).fetch();
    }

    @Override
    public List<LectureResult> getLectureResultByUserId(Long userid) {
        return dsl.select(
                        Projections.bean(
                                LectureResult.class,
                                lecture.id.as("id"),
                                lecture.name.as("name"),
                                lecture.instructorName.as("instructorName"),
                                lecture.location.as("location"),
                                lecture.lectureDate.as("lectureDate"),
                                lecture.dayInfo.as("dayInfo"),
                                lecture.startTime.as("startTime"),
                                lecture.endTime.as("endTime"),
                                lecture.isEnrollmentOpen.as("isEnrollmentOpen")
                        )
                )
                .from(student)
                .innerJoin(appliedLecture).on(appliedLecture.student.id.eq(userid))
                .innerJoin(lecture).on(lecture.eq(appliedLecture.lecture))
                .fetch();
    }

    private BooleanExpression idEq(Long id){
        return id != null ? student.id.eq(id):null;
    }
    private BooleanExpression nameEq(String name){
        return hasText(name) ? student.name.eq(name):null;
    }
    private BooleanExpression studentCodeEq(String studentCode){
        return hasText(studentCode) ? student.studentCode.eq(studentCode):null;
    }
}
