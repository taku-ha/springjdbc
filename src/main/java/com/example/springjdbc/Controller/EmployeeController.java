package com.example.springjdbc.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springjdbc.domain.Employee;
import com.example.springjdbc.service.EmployeeService;



@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/execute")
    public String execute(RedirectAttributes redirectAttributes) {
        Employee employee = new Employee();
        employee.setName("山田太郎");
        employee.setAge(20);
        employee.setGender("男");
        employee.setDepartmentId(1);
        service.save(employee);

        // List<String> idList = new ArrayList<>();
        // idList.add();
        //Employee employee2 = service.load(3);
        //System.out.println(employee2);
        //service.deleteById(3);
        service.findAll().forEach(System.out::println);
        return "finished";
    }
    
}
