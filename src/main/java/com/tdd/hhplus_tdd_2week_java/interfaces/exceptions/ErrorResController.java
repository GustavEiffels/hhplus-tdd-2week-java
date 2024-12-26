package com.tdd.hhplus_tdd_2week_java.interfaces.exceptions;


import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.DomainSettingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResController {

    @ExceptionHandler(value = DomainSettingException.class)
    public ResponseEntity<ResponseDto> handleException(DomainSettingException e) {
        return ResponseEntity.status(500).body(ResponseDto.builder().build());
    }

}
