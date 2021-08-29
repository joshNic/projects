package com.example.demo.configuration;

import com.example.demo.models.student.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student joshua = new Student(
                        1L,
                        "Joshua",
                        "joshua@gmail.com",
                        LocalDate.of(2000, Month.DECEMBER,15),
                        45
                );

            Student mugisha = new Student(
                    1L,
                    "Mugisha",
                    "mugisha@gmail.com",
                    LocalDate.of(2000, Month.DECEMBER,15),
                    45
            );
            studentRepository.saveAll(
                    List.of(joshua,mugisha)
            );
        };
    }
}
