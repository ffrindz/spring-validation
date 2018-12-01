package com.maatic;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    @PostMapping("/student")
    public Student home(@Valid @RequestBody Student student) {
        return student;
    }
}
