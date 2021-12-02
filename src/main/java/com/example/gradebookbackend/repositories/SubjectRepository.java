package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Group;
import com.example.gradebookbackend.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Group, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.subject_id <> 1 ORDER BY s.name")
    List<Subject> findAllWithoutTest();
}
