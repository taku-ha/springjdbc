package com.example.springjdbc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springjdbc.domain.Department;

@Repository
public class DepartmentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Department> DEPARTMENT_ROW_MAPPER = (rs, i) -> {
        Department department = new Department();
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));
        return department;
    };

    public List<Department> findAll() {
        String sql = """
                    select
                    id
                    ,name
                    from departments
                    order by
                    id
                """;
    List<Department> departmentList = template.query(sql, DEPARTMENT_ROW_MAPPER);
    return departmentList;
    }
}
