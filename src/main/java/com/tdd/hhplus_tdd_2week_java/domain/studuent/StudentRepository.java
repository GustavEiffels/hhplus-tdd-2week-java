package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
