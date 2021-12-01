package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface MarkRepository extends JpaRepository<Mark, Integer> {

//    @Query("SELECT m FROM Mark m ") //WHERE m.student_id=:student_id AND m.semester=:semester
//    List<Mark> getMarksByStudentSemester(/*@Param("student_id") Integer student_id, @Param("semester") Integer semester*/);
}
