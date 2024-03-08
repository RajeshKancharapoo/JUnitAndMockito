package com.example.sample.service;

import com.example.sample.entity.Student;
import com.example.sample.modelDTO.StudentDTO;
import com.example.sample.repository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStudent() {
        // Arrange
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setDepartment("Computer Science");
        studentDTO.setCollege("ABC College");

        Student expectedStudent = new Student();
        //expectedStudent.setId(1L);
        expectedStudent.setFirstName("John");
        expectedStudent.setLastName("Doe");
        expectedStudent.setEmail("john.doe@example.com");
        expectedStudent.setDepartment("Computer Science");
        expectedStudent.setCollege("ABC College");

        Mockito.when(studentRepo.save(Mockito.any(Student.class))).thenReturn(expectedStudent);

        // Act
        Student result = studentService.addStudent(studentDTO);

        // Assert
        Assertions.assertEquals(expectedStudent, result);
        Mockito.verify(studentRepo).save(Mockito.any(Student.class));
    }

    @Test
    public void testGetStudent() {
        // Arrange
        Long studentId = 1L;
        Student expectedStudent = new Student();
        //expectedStudent.setId(studentId);
        expectedStudent.setFirstName("John");
        expectedStudent.setLastName("Doe");
        expectedStudent.setEmail("john.doe@example.com");
        expectedStudent.setDepartment("Computer Science");
        expectedStudent.setCollege("ABC College");

        Mockito.when(studentRepo.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        // Act
        Student result = studentService.getStudent(studentId);

        // Assert
        Assertions.assertEquals(expectedStudent, result);
        Mockito.verify(studentRepo).findById(studentId);
    }

    @Test
    public void testAllStudents() {
        // Arrange
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student(1L, "John", "Doe", "john.doe@example.com", "Computer Science", "ABC College"));
        expectedStudents.add(new Student(2L, "Jane", "Smith", "jane.smith@example.com", "Mathematics", "XYZ College"));

        Mockito.when(studentRepo.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> result = studentService.allStudents();

        // Assert
        Assertions.assertEquals(expectedStudents, result);
        Mockito.verify(studentRepo).findAll();
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        Long studentId = 1L;
        String expectedMessage = "Student with id " + studentId + " has been deleted";

        // Act
        String result = studentService.deleteStudent(studentId);

        // Assert
        Assertions.assertEquals(expectedMessage, result);
        Mockito.verify(studentRepo).deleteById(studentId);
    }

    @Test
    public void testUpdateStudent() {
        // Arrange
        Long studentId = 1L;
        Student existingStudent = new Student();
       // existingStudent.setId(studentId);
        existingStudent.setFirstName("John");
        existingStudent.setLastName("Doe");
        existingStudent.setEmail("john.doe@example.com");
        existingStudent.setDepartment("Computer Science");
        existingStudent.setCollege("ABC College");

        Mockito.when(studentRepo.findById(studentId)).thenReturn(Optional.of(existingStudent));
        Mockito.when(studentRepo.save(Mockito.any(Student.class))).thenReturn(existingStudent);

        // Act
        Student result = studentService.updateStudent(studentId);

        // Assert
        Assertions.assertEquals(existingStudent, result);
        Assertions.assertEquals("John", result.getFirstName());
        Assertions.assertEquals("Doe", result.getLastName());
        Mockito.verify(studentRepo).findById(studentId);
        Mockito.verify(studentRepo).save(Mockito.any(Student.class));
    }
}