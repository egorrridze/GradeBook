package com.example.gradebookbackend.controllers;

import com.example.gradebookbackend.models.Mark;
import com.example.gradebookbackend.repositories.MarkRepository;
import com.example.gradebookbackend.services.MarkService;
import com.example.gradebookbackend.services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private MarkRepository markRepo;

    private MarkService service;

    @RequestMapping("/")
    public String viewStudentHomePage(Map<String, Object> model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails u = (MyUserDetails)authentication.getPrincipal();
        Integer userId = u.getUser_id();

        Iterable<Mark> marks = markRepo.findByStudent_id(userId); //markRepo.findAll();
        model.put("marks", marks);
        return "index";
    }

    @PostMapping("/")
    public String filterStudentMarksBySemester(@RequestParam(value = "semester", required = false) Integer semester, Map<String, Object> model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails u = (MyUserDetails)authentication.getPrincipal();
        Integer userId = u.getUser_id();

        Iterable<Mark> marks = markRepo.findByStudent_idAndSemester(userId, semester);
        model.put("marks", marks);

        return "index";
    }


    @RequestMapping("/home")
    public String viewTeacherHomePage(Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails u = (MyUserDetails)authentication.getPrincipal();
        Integer userId = u.getUser_id();

        Iterable<Mark> marks = markRepo.findByTeacher_id(userId); //markRepo.findAll();
        model.put("marks", marks);

        return "index";
    }

    @PostMapping("/home")
    public String filterTeacherMarksBySemester(@RequestParam(value = "semester", required = false) Integer semester, Map<String, Object> model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails u = (MyUserDetails)authentication.getPrincipal();
        Integer userId = u.getUser_id();

        Iterable<Mark> marks = markRepo.findByTeacher_idAndSemester(userId, semester);
        model.put("marks", marks);

        return "index";
    }
}
