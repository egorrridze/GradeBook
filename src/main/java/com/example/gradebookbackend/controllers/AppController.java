package com.example.gradebookbackend.controllers;

import com.example.gradebookbackend.models.Group;
import com.example.gradebookbackend.models.Mark;
import com.example.gradebookbackend.models.Subject;
import com.example.gradebookbackend.models.User;
import com.example.gradebookbackend.repositories.GroupRepository;
import com.example.gradebookbackend.repositories.MarkRepository;
import com.example.gradebookbackend.repositories.SubjectRepository;
import com.example.gradebookbackend.repositories.UserRepository;
import com.example.gradebookbackend.services.MarkService;
import com.example.gradebookbackend.services.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MarkRepository markRepo;
    @Autowired
    private SubjectRepository subjectRepo;

    private Integer getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails u = (MyUserDetails)authentication.getPrincipal();
        return u.getUser_id();
    }

    @RequestMapping("/")
    public String viewStudentHomePage(Map<String, Object> model) {

        Iterable<Mark> marks = markRepo.findByStudent_id(getCurrentUserId()); //markRepo.findAll();
        model.put("marks", marks);
        return "index";
    }

    @PostMapping("/")
    public String filterStudentMarksBySemester(@RequestParam(value = "semester", required = false) Integer semester, Map<String, Object> model){

        Iterable<Mark> marks = markRepo.findByStudent_idAndSemester(getCurrentUserId(), semester);
        model.put("marks", marks);

        return "index";
    }


    @RequestMapping("/home")
    public String viewTeacherHomePage(Map<String, Object> model) {

        Iterable<Mark> marks = markRepo.findByTeacher_id(getCurrentUserId());
        model.put("marks", marks);

        return "index";
    }

    @PostMapping("/home")
    public String filterTeacherMarksBySemester(@RequestParam(value = "semester", required = false) Integer semester, Map<String, Object> model) {

        Iterable<Mark> marks = markRepo.findByTeacher_idAndSemester(getCurrentUserId(), semester);
        model.put("marks", marks);

        return "index";
    }

    @RequestMapping("/new_mark")
    public String newMarkPage(@RequestParam(value = "student_choice", required = false) Integer student_choice, Map<String, Object> model) {

//        Iterable<Group> groups = groupRepo.findAllWithoutTest();
//        model.put("groups", groups);
        Iterable<Subject> subjects = subjectRepo.findAllWithoutTest();
        model.put("subjects", subjects);

        Iterable<User> students = userRepo.findStudents();
        model.put("students", students);

        return "new_mark";
    }

    @PostMapping("/new_mark")
    public String sendNewMark(@RequestParam Integer semester_choice, @RequestParam Integer subject_choice, @RequestParam Integer student_choice, @RequestParam Integer mark_choice, Map<String, Object> model) {
        Mark mark = new Mark(getCurrentUserId(), student_choice, subject_choice, mark_choice, semester_choice);
        System.out.println("student: " + mark.getStudent_id() + " subject: " + mark.getSubject_id());
        markRepo.save(mark);
        return "new_mark";

    }
}
