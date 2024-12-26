package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentResult create(StudentParam studentParam);


    // READ
    Optional<Student> readByCondition(StudentParam condition);

    // READ
    Optional<StudentResult> readByConditionWithResult(StudentParam condition);


    // READ ALL
    List<Student> readAllByCondition(StudentParam condition);

    // READ ALL
    List<StudentResult> readAllByConditionWithResult(StudentParam condition);


    // JOIN
    List<LectureResult> readLectureResultListById(Long userid);


    // isExistStudent
    Student isExistStudent(Long userId);


    List<LectureResult> getTodaySchedule(Long userId, LocalDate localDate);


    StudentResult convertToDto(Student student);

    Student convertToEntity(StudentParam studentParam);
}
