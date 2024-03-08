package com.example.sample.controller;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.example.sample.entity.Student;
import com.example.sample.modelDTO.StudentDTO;
import com.example.sample.service.StudentService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static java.lang.reflect.Array.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testAddStudent() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        StudentDTO studentDTO = StudentDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail")
                .department("Computer Science")
                .college("XYZ University")
                .build();
        Student student=Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail")
                .department("Computer Science")
                .college("XYZ University")
                .build();

        when(studentService.addStudent(studentDTO)).thenReturn(student);
        // Arrange
        mockMvc.perform(post("/api/student/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testGetStudent() throws Exception {
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        when(studentService.getStudent(studentId)).thenReturn(student);

       Assertions.assertEquals(student, studentService.getStudent(studentId));

       mockMvc.perform(MockMvcRequestBuilders.get("/api/student/get/1"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testAllStudents() throws Exception {
        // Arrange
        Student s1=Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        Student s2=Student.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("abc@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        List<Student> students = List.of(s1, s2);
        when(studentService.allStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/all"))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertEquals(students, studentService.allStudents()))
                .andDo(print());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        // Arrange
        Long studentId = 1L;
        String expected = "Student with id " + studentId + " has been deleted";
        when(studentService.deleteStudent(studentId)).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/delete?studentId=1"))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertEquals(expected, studentService.deleteStudent(studentId)))
                .andDo(print());

    }

    @Test
    public void testUpdateStudent() throws Exception {
        // Arrange
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        when(studentService.updateStudent(studentId)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/update?studentId=1"))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertEquals(student, studentService.updateStudent(studentId)))
                .andDo(print());

    }
}