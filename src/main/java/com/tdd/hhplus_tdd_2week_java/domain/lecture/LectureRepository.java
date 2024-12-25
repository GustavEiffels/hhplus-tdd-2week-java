package com.tdd.hhplus_tdd_2week_java.domain.lecture;

import com.tdd.hhplus_tdd_2week_java.infrastructure.lecture.LectureRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureRepositoryCustom {

}
