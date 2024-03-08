package com.example.sample.service;


import com.example.sample.entity.Student;
import com.example.sample.modelDTO.StudentDTO;
import com.example.sample.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public Student addStudent(StudentDTO studentDTO) {

        Student student = Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .department(studentDTO.getDepartment())
                .college(studentDTO.getCollege())
                .build();

        return studentRepo.save(student);
    }

    public Student getStudent(Long studentId) {
        return studentRepo.findById(studentId).orElse(null);

    }

    public List<Student> allStudents() {
        return studentRepo.findAll();
    }

    public String deleteStudent(Long studentId) {
        studentRepo.deleteById(studentId);
        return "Student with id " + studentId + " has been deleted";
    }

    public Student updateStudent(Long studentId) {
        Student student= studentRepo.findById(studentId).orElse(null);
        if (student != null) {
            student.setFirstName("John");
            student.setLastName("Doe");
        }
        return studentRepo.save(student);
    }
}
