package com.example.springjdbc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.springjdbc.domain.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setAge(rs.getInt("age"));
        employee.setGender(rs.getString("gender"));
        employee.setDepartmentId(rs.getInt("department_id"));
        return employee;
    };


    public Employee load(Integer id) {

        String sql = """
                    select
                        id
                        ,name
                        ,age
                        ,gender
                        ,department_id
                    from employees
                    where
                    id = :id
                """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    public List<Employee> findAll() {

        String sql = """
                    select
                        id
                        ,name
                        ,age
                        ,gender
                        ,department_id
                    from employees
                    order by
                    age
                """;

        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    public Employee save(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        if(employee.getId() == null) {
            String sql = """
                             insert into employees (
                                name
                                ,age
                                ,gender
                                ,department_id
                                ) values (
                                :name
                                ,:age
                                ,:gender
                                ,:departmentId
                                )returning id
                            """;
            // "INSERT INTO employees(name,age,gender,department_id)"
            //                 + " VALUES(:name,:age,:gender,:departmentId)";
            Integer id = template.queryForObject(sql, param, Integer.class);
            employee.setId(id);
        }else {
            String updateSql = """
                                update employees set   
                                    name=:name
                                    ,age=:age, 
                                    ,gender=:gender
                                    ,department_id=:departmentId
                                    where id=:id
                             """;
            
            
            
            
            // "UPDATE employees SET name=:name, age=:age, "
            //                 + " gender=:gender,department_id=:departmentId"
            //                 + " WHERE id=:id";
            
            template.update(updateSql, param);
        }
        return employee;
    }

    public void deleteById(Integer id) {

        String deleteSql = """
                            delete
                            from employees
                            where id=:id;
                """;
                SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        template.update(deleteSql, param);
    }

}
