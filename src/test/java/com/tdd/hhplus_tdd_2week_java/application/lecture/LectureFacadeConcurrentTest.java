package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
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
import java.util.concurrent.atomic.AtomicInteger;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class LectureFacadeConcurrentTest {

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

    public Lecture createLecture(int year, int mon, int day, int start, int end){
        return lectureRepository.save(lectureService.convertToEntity(LectureParam.builder()
                .name("바보들")
                .instructorName("김연습")
                .location("집202")
                .lectureDate(LocalDate.of(year,mon,day))
                .startTime(start)
                .endTime(end)
                .build()));
    }

    public Student createStudent(String name, String code){
        return studentRepository.save(new Student(name,code));
    }

    public AppliedLecture createAppliedLecture(Lecture lecture,Student student){
        return appliedLectureRepository.save(new AppliedLecture(lecture,student));
    }


    /**
     * 날짜 기준으로 신청 가능 목록 API
     * findListByLecture
     */
    @Test
    void findListByLecture() throws InterruptedException {

        // CREATE USER
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            students.add(studentRepository.save(new Student("김연습", String.valueOf(202102000+i))));
        }

        // CREATE  LECTURE
        Lecture createLecture = createLecture(2024,12,31,10,11);

        ExecutorService executorService = Executors.newFixedThreadPool(40);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(40);

        AtomicInteger atomicInteger = new AtomicInteger(1);

        for (int i = 0; i < 40; i++) {
            executorService.submit(() -> {
                try {
                    startLatch.await();
                    LectureApiDto.ApplyLectureReq req = LectureApiDto.ApplyLectureReq.builder()
                            .lectureId(createLecture.getId())
                            .userId(students.get(atomicInteger.getAndIncrement()).getId())
                            .build();
                    lectureFacade.applyLecture(req);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    endLatch.countDown();
                }
            });
        }
        startLatch.countDown();
        endLatch.await();


    }


}