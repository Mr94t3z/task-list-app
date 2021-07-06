package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    void deleteByDone(boolean done);
}
