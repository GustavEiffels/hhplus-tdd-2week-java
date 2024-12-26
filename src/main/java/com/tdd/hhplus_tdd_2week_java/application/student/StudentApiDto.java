package com.tdd.hhplus_tdd_2week_java.application.student;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

public interface StudentApiDto {

    @Getter
    @Setter
    @Builder
    class FindAppliedReq{
        private Long userid;
    }

    @Setter
    @Getter
    @SuperBuilder
    class FindAppliedRes extends ResponseDto {
        private List<LectureResult> lectureInfoList;
    }


}
