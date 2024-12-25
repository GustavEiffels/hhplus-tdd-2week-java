package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.*;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppliedLectureServiceImpl implements AppliedLectureService {

    private final AppliedLectureRepository repository;
    private final AppliedLectureServiceValidation validation;

    @Override
    public AppliedLectureResult create(AppliedLectureParam param) {
        validation.isConditionFieldNotNull(param.getLecture());
        validation.isConditionFieldNotNull(param.getStudent());

        AppliedLectureParam conditionParam = AppliedLectureParam.builder()
                .student(param.getStudent())
                .lecture(param.getLecture())
                .build();

        Optional<AppliedLecture> optionalAppliedLecture = repository.findByCondition(conditionParam);

        if(optionalAppliedLecture.isPresent()){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.ALREADY_EXIST);
        }

        AppliedLecture newAppliedLecture = convertToEntity(param);

        repository.save(newAppliedLecture);

        return  convertToDto(newAppliedLecture);
    }

    @Override
    public AppliedLectureResult update(AppliedLectureParam findParam, AppliedLectureParam updateParam) {
        validation.isConditionFieldNotNull(findParam.getLecture());
        AppliedLectureParam conditionParam = AppliedLectureParam.builder()
                .lecture(findParam.getLecture())
                .build();

        Optional<AppliedLecture> optionalAppliedLecture = repository.findByCondition(conditionParam);

        if(optionalAppliedLecture.isEmpty()){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.NOT_EXIST);
        }

        AppliedLecture findAppliedLecture = optionalAppliedLecture.get();
        findAppliedLecture.updateLectureInfo(updateParam.getLecture());

        return convertToDto(findAppliedLecture);
    }

    @Override
    public Optional<AppliedLectureResult> findByCondition(AppliedLectureParam findParam) {
        return Optional.empty();
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
