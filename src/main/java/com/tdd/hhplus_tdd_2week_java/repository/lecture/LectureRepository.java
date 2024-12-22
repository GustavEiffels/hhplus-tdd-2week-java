package com.tdd.hhplus_tdd_2week_java.repository.lecture;

import com.tdd.hhplus_tdd_2week_java.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureRepositoryCustom {
}
