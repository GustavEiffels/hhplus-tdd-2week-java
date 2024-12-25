package com.tdd.hhplus_tdd_2week_java.domain.studuent.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS.ALREADY_EXIST;
import static com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS.NOT_EXIST_STUDENT;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentServiceValidate serviceValidate;

    @Override
    public StudentResult create(StudentParam studentParam) {
        serviceValidate.isConditionFieldNotNull(studentParam.getStudentCode());

        Optional<Student> findByCondition = repository.findByCondition(studentParam);

        if(findByCondition.isPresent()){
            throw new StudentSettingException(ALREADY_EXIST);
        }

        Student newStudent = repository.save(convertToEntity(studentParam));

        return convertToDto(newStudent);

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
