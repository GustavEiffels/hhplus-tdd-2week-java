package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import com.tdd.hhplus_tdd_2week_java.infrastructure.student.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {
}
