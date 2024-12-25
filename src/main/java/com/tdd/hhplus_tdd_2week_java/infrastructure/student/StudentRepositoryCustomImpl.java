package com.tdd.hhplus_tdd_2week_java.infrastructure.student;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
                            nameEq(condition.getName()),
                            studentCodeEq(condition.getStudentCode()))
                    .fetchOne()
        );
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
