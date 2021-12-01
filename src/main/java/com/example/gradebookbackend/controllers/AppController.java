package com.example.gradebookbackend.controllers;

import com.example.gradebookbackend.models.Mark;
import com.example.gradebookbackend.services.MarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {

    private MarkService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
//        List<Mark> listMarks = service.listAll();
//        model.addAttribute("listMarks", listMarks);

        return "index";
    }
}
