package com.tdd.hhplus_tdd_2week_java.domain.studuent.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentServiceValidate studentServiceValidate;

    @Override
    public StudentResult create(StudentParam studentParam) {
        Student newStudent = convertToEntity(studentParam);
        return convertToDto(repository.save(newStudent));
    }

    @Override
    public Optional<Student> readByCondition(StudentParam condition) {
        return repository.findByCondition(condition);
    }

    @Override
    public Optional<StudentResult> readByConditionWithResult(StudentParam condition) {
        return repository.findByConditionWithResult(condition);
    }

    @Override
    public List<Student> readAllByCondition(StudentParam condition) {
        return repository.findAllByCondition(condition);
    }

    @Override
    public List<StudentResult> readAllByConditionWithResult(StudentParam condition) {
        return repository.findAllByConditionWithResult(condition);
    }

    @Override
    public List<LectureResult> readLectureResultListById(Long userid) {
        return repository.getLectureResultByUserId(userid);
    }

    @Override
    public Student isExistStudent(Long userId) {
        studentServiceValidate.isConditionFieldNotNull(userId);
        Optional<Student> optionalStudent = readByCondition(StudentParam.builder().id(userId).build());

        if(optionalStudent.isEmpty()){
            throw new StudentSettingException(NOT_EXIST_STUDENT);
        }

        return optionalStudent.get();
    }

    @Override
    public List<LectureResult> getTodaySchedule(Long userId, LocalDate localDate) {
        isExistStudent(userId);

        if(localDate == null){
            throw new StudentSettingException(NOT_ENOUGH_FIELD);
        }

        return repository.getLectureResult(userId,localDate);
    }

    @Override
    public StudentResult convertToDto(Student student) {
        return StudentResult.builder()
                .id(student.getId())
                .name(student.getName())
                .studentCode(student.getStudentCode())
                .build();
    }

    @Override
    public Student convertToEntity(StudentParam studentParam) {
        return new Student(studentParam.getName(),studentParam.getStudentCode());
    }


}
