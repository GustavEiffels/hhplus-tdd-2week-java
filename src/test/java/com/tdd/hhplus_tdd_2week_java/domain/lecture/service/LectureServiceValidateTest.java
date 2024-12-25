package com.tdd.hhplus_tdd_2week_java.domain.lecture.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LectureServiceValidateTest {

    private final LectureServiceValidate lectureServiceValidate = new LectureServiceValidate();
    /**
     * 강의가 서로 겹치는지
     * - 강의 시간이 서로 겹치는 지 확인
     * exception
     * 기존 강의 : old
     *  시작 - os
     *  종료 - oe
     *
     * 새로운 강의 : new
     *  시작 - ns
     *  종료 - ne
     */
    @Test
    void isLecture_conflict(){
        int os = 10;
        int oe = 13;
        int ns = 13;
        int ne = 14;

        lectureServiceValidate.isLectureConflict(os,oe,ns,ne);

        int os_0 = 10;
        int oe_0 = 13;
        int ns_0 = 9;
        int ne_0 = 10;

        lectureServiceValidate.isLectureConflict(os_0,oe_0,ns_0,ne_0);
    }

    private void isLectureConflict_matcher(int os,int oe, int ns, int ne){
        LectureSettingException exception = Assertions.assertThrows(LectureSettingException.class,()->{
            lectureServiceValidate.isLectureConflict(os,oe,ns,ne);
        });

        Assertions.assertEquals(LECTURE_STATUS.LECTURE_CONFLICT,exception.getStatus());
    }
    @Test
    void isLecture_conflict_exception(){
        isLectureConflict_matcher(10,11,10,12);
        isLectureConflict_matcher(10,13,11,12);
        isLectureConflict_matcher(10,13,9,12);
        isLectureConflict_matcher(10,13,11,12);
        lectureServiceValidate.isLectureConflict(10,11,11,12);
        lectureServiceValidate.isLectureConflict(10,11,9,10);
    }

    /**
     * isConditionAssign
     * LectureParam 의 필드 값이 모두 null 일때
     */
    @Test
    void isConditionAssign_Exception(){
        LectureParam lectureParam = LectureParam.builder().build();

        LectureSettingException exception = Assertions.assertThrows(LectureSettingException.class,()->{
            lectureServiceValidate.isConditionAssign(lectureParam);
        });
        Assertions.assertEquals(LECTURE_STATUS.NOT_ENOUGH_FIELD,exception.getStatus());

        lectureParam.setLocation("김연습");
        lectureServiceValidate.isConditionAssign(lectureParam);
    }
}