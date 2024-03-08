package com.example.sample.repository;


import com.example.sample.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentRepoTests {

    @Autowired
    private StudentRepo studentRepo;


    @Test
    public void testAddStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .department("Computer Science")
                .college("XYZ University")
                .build();

        //when(studentRepo.save(Mockito.any(Student.class))).thenReturn(student);
        Student ans=studentRepo.save(student);
        Assertions.assertThat(ans.getStudentId()).isGreaterThan(0);
    }

    @Test
    public void testGetStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        Student ans=studentRepo.save(student);
        Assertions.assertThat(ans.getStudentId()).isGreaterThan(0);
    }

    @Test
    public void testAllStudents() {
        Student s1 = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .department("Computer Science")
                .college("XYZ University")
                .build();

        Student s2 = Student.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("xyz@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        studentRepo.save(s1);
        studentRepo.save(s2);

        List<Student> students = studentRepo.findAll();

        Assertions.assertThat(students.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .department("Computer Science")
                .college("XYZ University")
                .build();

        Student ans=studentRepo.save(student);
        studentRepo.deleteById(ans.getStudentId());
        Assertions.assertThat(studentRepo.findById(ans.getStudentId())).isEmpty();
    }

    @Test
    public void testUpdateStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .college("XYZ University")
                .department("Computer Science")
                .build();

        Student ans=studentRepo.save(student);
        ans.setFirstName("Jane");
        ans.setLastName("Doe");
        studentRepo.save(ans);
        Assertions.assertThat(studentRepo.findById(ans.getStudentId()).get().getFirstName()).isEqualTo("Jane");
    }

}
