package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.service.LectureServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureFacade {

    private final LectureService lectureService;
    private final LectureServiceValidate lectureServiceValidate;
    private final StudentService studentService;
    private final StudentServiceValidate studentServiceValidate;


    /**
     * 날짜 기준으로 신청 가능 목록 API
     * @param request
     * @return
     */
    public ResponseDto findListByLecture(LectureApiDto.FindByLocalReq request){
        // request 객체 null 인지 확인
        lectureServiceValidate.isConditionFieldNotNull(request.getLocalDate());

        // 검색 조건 생성
        LectureParam condition = LectureParam.builder()
                .lectureDate(request.getLocalDate())
                .isEnrollmentOpen(true)
                .build();

        // 검색 결과 가져오기
        List<LectureResult> lectures = lectureService.readAllWithResult(condition);

        return LectureApiDto.FindByLocalRes.builder()
                .lectureList(lectures)
                .build();
    }


}
