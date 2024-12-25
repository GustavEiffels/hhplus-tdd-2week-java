package com.tdd.hhplus_tdd_2week_java.common.custom_exceptions;

import lombok.Getter;

@Getter
public class DomainSettingException extends RuntimeException {
    public DomainSettingException(String message){
        super(message);
    }
}
