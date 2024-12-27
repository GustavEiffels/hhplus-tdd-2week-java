package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.APPLIED_LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class ConcurrentTest {

    @Autowired
    LectureFacade lectureFacade;

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    AppliedLectureRepository appliedLectureRepository;

    @Autowired
    StudentRepository studentRepository;


    List<Student> studentList = new ArrayList<>();

    Lecture lecture;

    @BeforeEach
    void setUp(){

        for(int i = 1; i<=40 ;i++){
            Student newStudent = studentRepository.save(new Student("김연습",String.valueOf(202401000+i)));
            System.out.println(newStudent.getId());
            System.out.println(newStudent.getName());
            System.out.println(newStudent.getStudentCode());
            studentList.add(newStudent);
        }

        lecture = lectureRepository.save(
                lectureService.convertToEntity(
                        LectureParam.builder()
                                .name("연습게임")
                                .instructorName("이조교")
                                .location("3층 101호")
                                .lectureDate(LocalDate.of(2024,12,31))
                                .startTime(9)
                                .endTime(10)
                                .build()
                ));

        System.out.println(lecture.getId());
    }

    @Test
    void test2(){
        try{
            lectureFacade.applyLecture(LectureApiDto.ApplyLectureReq.builder()
                    .lectureId(lecture.getId())
                    .userId(studentList.get(0).getId()).build());

            System.out.println("성공");
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        CountDownLatch latch = new CountDownLatch(10);

        // 40명 동시에 강의 신청
        List<Future<ResponseDto>> futures = new ArrayList<>();
        for (Student student : studentList) {
            futures.add(executorService.submit(() -> {
                try {
                    return lectureFacade.applyLecture(
                            LectureApiDto.ApplyLectureReq.builder()
                                    .lectureId(lecture.getId())
                                    .userId(student.getId())
                                    .build());
                } catch (Exception e) {
                    System.out.println("실패: " + e.getLocalizedMessage());
                    return null; // 기본 반환값
                } finally {
                    latch.countDown();
                }
            }));
        }

        // 대기
        latch.await();  // 여기서 대기

        executorService.shutdown();  // 모든 작업이 끝난 후에 shutdown 호출

        // 성공 및 실패 확인
        int successCount = 0;
        int failureCount = 0;

        // 대기
        latch.await();
        executorService.shutdown();


        for (Future<ResponseDto> future : futures) {
            try {
                if (future.get() != null) {
                    successCount++;
                } else {
                    failureCount++;
                }
            } catch (Exception e) {
                failureCount++;
            }
        }

        System.out.println("성공: " + successCount + ", 실패: " + failureCount);

        assertEquals(30, successCount, "최대 30명만 신청에 성공해야 합니다.");
        assertEquals(10, failureCount, "10명은 신청에 실패해야 합니다.");
    }

}