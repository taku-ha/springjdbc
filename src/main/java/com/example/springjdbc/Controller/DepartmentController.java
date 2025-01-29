package com.example.springjdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springjdbc.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping("/execute")
    public String execute() {
    service.findAll().forEach(System.out::println);
    return "finish";
    }
}