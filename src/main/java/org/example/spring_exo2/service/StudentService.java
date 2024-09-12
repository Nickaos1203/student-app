package org.example.spring_exo2.service;

import org.example.spring_exo2.dao.StudentRepository;
import org.example.spring_exo2.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void update(Long id, Student updateStudent) {
        Student studentExist = findById(id);
        if(studentExist != null) {
            studentExist.setFirstName(updateStudent.getFirstName());
            studentExist.setLastName(updateStudent.getLastName());
            studentExist.setAge(updateStudent.getAge());
            studentExist.setMail(updateStudent.getMail());
        }

        studentRepository.save(studentExist);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public Student findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

}