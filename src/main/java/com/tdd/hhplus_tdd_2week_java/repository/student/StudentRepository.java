package com.tdd.hhplus_tdd_2week_java.repository.student;

import com.tdd.hhplus_tdd_2week_java.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>,StudentRepositoryCustom {
}
