package com.example.tasklist.repository;

import com.example.tasklist.Status;
import com.example.tasklist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUser(String fullName);

    List<Task> findAllByUserAndStatus(String fullName, Status status);
}
