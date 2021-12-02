package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface MarkRepository extends JpaRepository<Mark, Integer> {

    List<Mark> findBySemester(Integer semester);

    @Query("SELECT m FROM Mark m WHERE m.student_id = :username")
    List<Mark> findByStudent_id(@Param("username") Integer username);

    @Query("SELECT m FROM Mark m WHERE m.student_id = :username AND m.semester = :semester")
    List<Mark> findByStudent_idAndSemester(@Param("username") Integer username, @Param("semester") Integer semester);

    @Query("SELECT m FROM Mark m WHERE m.teacher_id = :username")
    List<Mark> findByTeacher_id(@Param("username") Integer username);

    @Query("SELECT m FROM Mark m WHERE m.teacher_id = :username AND m.semester = :semester")
    List<Mark> findByTeacher_idAndSemester(@Param("username") Integer username, @Param("semester") Integer semester);
}
