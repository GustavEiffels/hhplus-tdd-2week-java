package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.infrastructure.applied_lecture.AppliedLectureRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppliedLectureRepository extends JpaRepository<AppliedLecture, Long>, AppliedLectureRepositoryCustom {

}

