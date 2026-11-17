
package com.example.librarysystem;

import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DataController {

    private final JdbcTemplate jdbc;

    public DataController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/data")
    public List<Map<String, Object>> getData() {
        return jdbc.queryForList("SELECT * FROM books");
    }

    @PostMapping("/data")
    public String addData(@RequestParam String name) {
        jdbc.update(
            "INSERT INTO books(name) VALUES (?)", name
        );
        return "Data added successfully";
    }

    @DeleteMapping("/data/{id}")
    public String deleteData(@PathVariable int id) {
        jdbc.update("DELETE FROM books WHERE id = ?", id);
        return "Data deleted successfully";
    }
}
