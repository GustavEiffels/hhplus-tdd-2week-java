package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.service;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.*;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppliedLectureServiceImpl implements AppliedLectureService {

    private final AppliedLectureRepository repository;
    private final AppliedLectureServiceValidation validation;

    /**
     * CREATE
     * @param param
     * @return
     */
    @Override
    public AppliedLectureResult create(AppliedLectureParam param) {
        AppliedLecture newAppliedLecture = convertToEntity(param);
        return convertToDto(repository.save(newAppliedLecture));
    }

    /**
     * UPDATE
     * @param exist
     * @param updateParam
     * @return
     */
    @Override
    public AppliedLecture updateWithEntity(AppliedLecture exist, AppliedLectureParam updateParam) {
        exist.updateLectureInfo(updateParam.getLecture());
        return exist;
    }

    /**
     * UPDATE
     * @param exist
     * @param updateParam
     * @return
     */
    @Override
    public AppliedLectureResult updateWithResult(AppliedLecture exist, AppliedLectureParam updateParam) {
        return convertToDto(updateWithEntity(exist,updateParam));
    }

    /**
     * READ
     * @param condition
     * @return
     */
    @Override
    public Optional<AppliedLecture> readWithEntity(AppliedLectureParam condition) {
        return repository.findByCondition(condition);
    }

    /**
     * READ
     * @param condition
     * @return
     */
    @Override
    public Optional<AppliedLectureResult> readWithResult(AppliedLectureParam condition) {
        return repository.findByConditionWithResult(condition);
    }

    /**
     * READ ALL
     * @param condition
     * @return
     */
    @Override
    public List<AppliedLecture> readAllWithEntity(AppliedLectureParam condition) {
        return repository.findAllByCondition(condition);
    }


    /**
     * READ ALL
     * @param condition
     * @return
     */
    @Override
    public List<AppliedLectureResult> readAllWithResult(AppliedLectureParam condition) {
        return repository.findAllByConditionWithResult(condition);
    }

    @Override
    public Optional<AppliedLecture> isExistAppliedLecture(AppliedLectureParam appliedLectureParam) {
        validation.isConditionFieldNotNull(appliedLectureParam.getStudent());
        validation.isConditionFieldNotNull(appliedLectureParam.getLecture());
        return readWithEntity(appliedLectureParam);
    }

    @Override
    public AppliedLectureResult convertToDto(AppliedLecture appliedLecture) {
        return  AppliedLectureResult.builder()
                .id(appliedLecture.getId())
                .lecture(appliedLecture.getLecture())
                .student(appliedLecture.getStudent())
                .endTime(appliedLecture.getEndTime())
                .startTime(appliedLecture.getStartTime())
                .lectureDate(appliedLecture.getLectureDate())
                .build();
    }

    @Override
    public AppliedLecture convertToEntity(AppliedLectureParam appliedLectureParam) {
        return new AppliedLecture(appliedLectureParam.getLecture(),appliedLectureParam.getStudent());
    }
}
