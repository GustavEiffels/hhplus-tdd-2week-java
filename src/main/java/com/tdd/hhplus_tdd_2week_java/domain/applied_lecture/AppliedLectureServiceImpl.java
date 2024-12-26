package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.*;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.common.CommonValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppliedLectureServiceImpl implements AppliedLectureService {

    private final AppliedLectureRepository repository;
    private final CommonValidation validation;

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
     * READ
     * @param condition
     * @return
     */
    @Override
    public Optional<AppliedLecture> readWithEntity(AppliedLectureParam condition) {
        return repository.findByCondition(condition);
    }


    @Override
    public List<AppliedLecture> readAllWithEntityLock(AppliedLectureParam condition) {
        return repository.findAllByConditionLock(condition);
    }



    @Override
    public Optional<AppliedLecture> isExistAppliedLectureWithLock(AppliedLectureParam appliedLectureParam) {
        return repository.findByIdWithLock(appliedLectureParam);
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