package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.service;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureService;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppliedLectureServiceImpl implements AppliedLectureService {

    private final AppliedLectureRepository appliedLectureRepository;

    @Override
    public AppliedLectureResult create(AppliedLectureParam param) {
        return null;
    }
}
