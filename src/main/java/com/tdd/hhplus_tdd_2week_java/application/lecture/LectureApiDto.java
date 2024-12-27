package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

public interface LectureApiDto {

    @Getter
    @Setter
    @Builder
    class FindByLocalReq{
        private LocalDate localDate;
    }

    @Getter
    @Setter
    @SuperBuilder
    class FindByLocalRes extends ResponseDto {
        private List<LectureResult> lectureList;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class ApplyLectureReq{
        private Long userId;
        private Long lectureId;
    }

    @Getter
    @Setter
    @SuperBuilder
    class ApplyLectureRes extends ResponseDto{

    }


}
