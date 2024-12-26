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
    private final LectureRepository  repository     ;

    @Override
    public LectureResult create(LectureParam lectureParam) {
        Lecture newLecture = repository.save(convertToEntity(lectureParam));
        return convertToResult(newLecture);
    }

    @Override
    public LectureResult updateWithResult(Lecture existLecture, LectureParam updateParam) {
        return convertToResult( updateWithEntity(existLecture,updateParam) );
    }

    @Override
    public Lecture       updateWithEntity(Lecture existLecture, LectureParam updateParam) {
        if(hasText(updateParam.getName())){
            existLecture.updateLectureName(updateParam.getName());
        }
        if(hasText(updateParam.getInstructorName())){
            existLecture.updateInstructorName(updateParam.getInstructorName());
        }
        if(hasText(updateParam.getLocation())){
            existLecture.updateLocation(updateParam.getLocation());
        }
        if(updateParam.getLectureDate() != null){
            existLecture.updateLectureDate(updateParam.getLectureDate());
        }
        if( updateParam.getStartTime() != null ){
            existLecture.updateStartTime(updateParam.getStartTime());
        }
        if( updateParam.getEndTime() != null ){
            existLecture.updateEndTime(updateParam.getEndTime());
        }
        return existLecture;
    }

    @Override
    public Optional<Lecture> readWithEntity(LectureParam condition) {
        return repository.findByCondition(condition);
    }

    @Override
    public Optional<LectureResult> readWithResult(LectureParam condition) {
        return repository.findByConditionWithResult(condition);
    }

    @Override
    public List<Lecture> readAllWithEntity(LectureParam condition) {
        return repository.findAllByCondition(condition);
    }

    @Override
    public List<LectureResult> readAllWithResult(LectureParam condition) {
        return repository.findAllByConditionWithResult(condition);
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
    public LectureResult convertToResult(Lecture lecture) {
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
