package com.example.springjdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jdbc")
public class jdbcController {

    @Autowired
    private NamedParameterJdbcTemplate templete;

    @RequestMapping("/execute")
    public String execute() {
        String sql = "SELECT count(*) FROM employees WHERE id = :firstId OR id = :secondId;";
        SqlParameterSource param = new MapSqlParameterSource()
                        .addValue("firstId" , 1).addValue("secondId", 3);
        Integer result = templete.queryForObject(sql, param, Integer.class);
        System.out.println("result = " + result);
        return "finished";
    }

}
