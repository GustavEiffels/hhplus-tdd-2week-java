package com.tdd.hhplus_tdd_2week_java.domain.studuent;


import com.tdd.hhplus_tdd_2week_java.domain.BaseEntity;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
/**
 * policy
 * 생성
 * - 이름은 한글 또는 영어만 사용 가능하며 50자 내외
 * - 학번은 2024_01_001 이렇게 9 글자 ( 숫자만 ) 이어야 하며
 *      첫번째 4글자는  최소 2020 이며 최대는 아직 2024 까지만
 *      두번째 2글자는  01 부터 09 까지 학번을 나타냄
 *      세번째 3글자는  001 부터 100 까지 학생 수를 나타냄 101 , 000 없음
 *
 */
public class Student extends BaseEntity {

    // 모든 Entity 의 id 값을 long 으로 사용한다는 보장이 없음
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String studentCode;


    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @Builder.Default
    public List<AppliedLecture> appliedLectures = new ArrayList<>();
}