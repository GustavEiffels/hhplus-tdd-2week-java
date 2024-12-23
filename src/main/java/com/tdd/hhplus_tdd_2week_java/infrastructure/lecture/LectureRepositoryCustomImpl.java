package com.tdd.hhplus_tdd_2week_java.infrastructure.lecture;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {
    private final JPAQueryFactory dsl;

    @Override
    public void findByLecturePid() {

    }
}
