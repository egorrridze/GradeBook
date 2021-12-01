package com.example.gradebookbackend.services;

import com.example.gradebookbackend.models.Mark;
import com.example.gradebookbackend.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkRepository repo;

//    public List<Mark> getMarksByStudentSemester(/*Integer student_id, Integer semester*/) {
//        return repo.getMarksByStudentSemester(/*student_id, semester*/);
//    }

    public List<Mark> listAll() {
        return repo.findAll();
    }
}
