package com.example.springjdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springjdbc.domain.Department;
import com.example.springjdbc.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> findAll() {
        return repository.findAll();
    }
}
