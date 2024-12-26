package com.tdd.hhplus_tdd_2week_java.interfaces.http;

import com.tdd.hhplus_tdd_2week_java.application.lecture.LectureApiDto;
import com.tdd.hhplus_tdd_2week_java.application.lecture.LectureFacade;
import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureFacade lectureFacade;

    @PostMapping("/search")
    public ResponseEntity<ResponseDto> findByDate(@RequestBody LectureApiDto.FindByLocalReq req){
        return new ResponseEntity<>(lectureFacade.findListByLecture(req), HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<ResponseDto> apply(@RequestBody LectureApiDto.ApplyLectureReq req){
        return new ResponseEntity<>(lectureFacade.applyLecture(req), HttpStatus.OK);
    }

}
