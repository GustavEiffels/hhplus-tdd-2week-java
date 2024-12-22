package com.tdd.hhplus_tdd_2week_java.repository.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.entity.AppliedLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppliedLectureRepository extends JpaRepository<AppliedLecture, Long>,AppliedLectureRepositoryCustom {
}
