package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureService;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class LectureFacadeConcurrencyTest {
    @Autowired
    StudentService studentService;

    @Autowired
    LectureService lectureService;

    @Autowired
    AppliedLectureService appliedLectureService;


    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LectureFacade lectureFacade;

    private Lecture lecture;
    private Student student;

    @BeforeEach
    void setUp(){

        lecture = lectureService.convertToEntity(LectureParam.builder()
                .name("test")
                .instructorName("test")
                .location("303호")
                .lectureDate(LocalDate.of(2024,12,31))
                .startTime(9)
                .endTime(11)
                .build());

        student = studentService.convertToEntity(StudentParam.builder()
                        .studentCode("202202022")
                        .name("김태호")
                        .build());

        lectureRepository.save(lecture);
        studentRepository.save(student);
    }

    private LectureApiDto.ApplyLectureReq createApplyRequest(Long studentId, Long lectureId) {
        return LectureApiDto.ApplyLectureReq.builder()
                .userId(studentId)
                .lectureId(lectureId)
                .build();
    }

    @Test
    void concurrency_30() throws InterruptedException {
        int threadCount = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Callable<Void> applyTask = () -> {
            try {
                lectureFacade.applyLecture(createApplyRequest(student.getId(), lecture.getId()));
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            } finally {
                latch.countDown();
            }
            return null;
        };

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(applyTask);
        }

        latch.await(10, TimeUnit.SECONDS);

        List<AppliedLecture> currentAppliedLecture = appliedLectureService.readAllWithEntityLock(
                AppliedLectureParam.builder().lecture(lecture).build()
        );

    }


}
