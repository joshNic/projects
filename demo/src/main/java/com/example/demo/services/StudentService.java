package com.example.demo.services;

import com.example.demo.models.student.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
//        return List.of(
//                new Student(
//                        1L,
//                        "Joshua",
//                        "joshua@gmail.com",
//                        LocalDate.of(2000, Month.DECEMBER,15),
//                        45
//                )
//        );
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException(
                    "student with id "+id+" does not exist");
        }
        studentRepository.deleteById(id);

    }
@Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()->new IllegalStateException(
                        "student with id "+studentId+" does not exist")

        );
        if (name!=null &&
        name.length()>0&&
        !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if (email!=null &&
                email.length()>0&&
                !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email already exists");
            }
            student.setEmail(email);
        }
    }
}
