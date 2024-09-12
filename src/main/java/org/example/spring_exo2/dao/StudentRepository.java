package org.example.spring_exo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.spring_exo2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByLastName(String lastName);


}
