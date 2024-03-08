package com.example.sample.controller;

import com.example.sample.entity.Student;
import com.example.sample.modelDTO.StudentDTO;
import com.example.sample.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Student addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("get/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/all")
    public List<Student>allStudents() {
        return studentService.allStudents();
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestParam Long studentId) {
        return studentService.updateStudent(studentId);
    }
}
