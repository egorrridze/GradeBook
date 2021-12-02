package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("SELECT g FROM Group g WHERE g.group_id <> 1 ORDER BY g.name")
    List<Group> findAllWithoutTest();
}
