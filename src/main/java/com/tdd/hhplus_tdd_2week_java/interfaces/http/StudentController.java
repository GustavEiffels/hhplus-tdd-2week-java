package com.tdd.hhplus_tdd_2week_java.interfaces.http;


import com.tdd.hhplus_tdd_2week_java.application.student.StudentApiDto;
import com.tdd.hhplus_tdd_2week_java.application.student.StudentFacade;
import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentFacade studentFacade;


    @PostMapping("/applied")
    public ResponseEntity<ResponseDto> findAppliedLecture(StudentApiDto.FindAppliedReq req){
        return new ResponseEntity<>(studentFacade.findAppliedLecture(req), HttpStatus.OK);
    }
}
