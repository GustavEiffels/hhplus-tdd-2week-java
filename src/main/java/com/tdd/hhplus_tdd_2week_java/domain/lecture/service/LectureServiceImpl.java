package com.tdd.hhplus_tdd_2week_java.domain.lecture.service;


import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.*;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository  repository ;
    private final LectureServiceValidate validate   ;

    /**
     * 1. 같은 공간, 같은 날짜에 강의 있는지 확인
     * 2. 존재할 경우 시간 까지 확인
     * @param newLectureParam
     * @return
     */
    @Override
    public LectureResult create(LectureParam newLectureParam) {

        // 1. 같은 공간, 같은 날짜에 강의가 있는지 확인
        LectureParam condition = LectureParam.builder()
                .lectureDate(validate.isConditionFieldNotNull(newLectureParam.getLectureDate()))
                .location(validate.isConditionFieldNotNull(newLectureParam.getLocation()))
                .build();

        Optional<Lecture> findByCondition = repository.findByCondition(condition);

        // 2. 존재하는 경우 시간이 겹치는 지 확인
        if(findByCondition.isPresent()) {
            Lecture findLectureSamePlace = findByCondition.get();
            // 시간이 겹치는 경우 예외 발생
            validate.isLectureConflict(
                    findLectureSamePlace.getStartTime(),
                    findLectureSamePlace.getEndTime(),
                    validate.isConditionFieldNotNull(newLectureParam.getStartTime()),
                    validate.isConditionFieldNotNull(newLectureParam.getEndTime())
                    );
        }
        // 3. 그렇지 않은 경우 강의 생성 성공
        Lecture newLecture =  repository.save(convertToEntity(newLectureParam));
        return convertToDto(newLecture);
    }

    /**
     * 1. 조회 조건이 비어있지는 않은지 확인
     * 2. 조건으로 특정 lecture 를 조회
     * 3. 수정 조건에 맞게 수정
     * @param findParam
     * @param updateParam
     * @return
     */
    @Override
    public LectureResult update(LectureParam findParam, LectureParam updateParam) {
        validate.isConditionAssign(findParam);

        Optional<Lecture> findByCondition = repository.findByCondition(findParam);

        if(findByCondition.isEmpty()){
            throw new LectureSettingException(LECTURE_STATUS.NOT_FOUND_LECTURE);
        }
        Lecture lecture = findByCondition.get();

        if(hasText(updateParam.getName()))          {
            lecture.updateLectureName(updateParam.getName());
        }
        if(hasText(updateParam.getInstructorName())){
            lecture.updateInstructorName(updateParam.getInstructorName());
        }
        if(hasText(updateParam.getLocation())){
            lecture.updateLocation(updateParam.getLocation());
        }
        if(updateParam.getLectureDate() != null){
            lecture.updateLectureDate(updateParam.getLectureDate());
        }
        if( updateParam.getStartTime() != null ){
            lecture.updateStartTime(updateParam.getStartTime());
        }
        if( updateParam.getEndTime() != null ){
            lecture.updateEndTime(updateParam.getEndTime());
        }

        return convertToDto(lecture);
    }

    /**
     * 1. 조회 조건 비어 있는지 확인
     * 2. 조회 -> 다건
     * @param param
     * @return
     */
    @Override
    public List<LectureResult>    readAllByCondition(LectureParam param) {
        validate.isConditionAssign(param);
        return repository.findAllByCondition(param);
    }

    /**
     * 1. 조회 조건 비어 있는지 확인
     * 2. 조회 -> 단건
     * @param param
     * @return
     */
    @Override
    public Optional<LectureResult> readByCondition(LectureParam param) {
        validate.isConditionAssign(param);
        Optional<Lecture> findByCondition = repository.findByCondition(param);
        return findByCondition.map(this::convertToDto).or(Optional::empty);
    }

    @Override
    public Lecture convertToEntity(LectureParam lectureParam) {
        return new Lecture(
                lectureParam.getName(),
                lectureParam.getInstructorName(),
                lectureParam.getLocation(),
                lectureParam.getLectureDate(),
                lectureParam.getStartTime(),
                lectureParam.getEndTime());
    }

    @Override
    public LectureResult convertToDto(Lecture lecture) {
        return LectureResult.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .instructorName(lecture.getInstructorName())
                .location(lecture.getLocation())
                .lectureDate(lecture.getLectureDate())
                .dayInfo(lecture.getDayInfo())
                .startTime(lecture.getStartTime())
                .endTime(lecture.getEndTime())
                .build();
    }


}