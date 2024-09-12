package org.example.spring_exo2.controller;

import jakarta.validation.Valid;
import org.example.spring_exo2.model.Student;
import org.example.spring_exo2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/")
    public String homePage() {
        return "homepage";
    }

    @RequestMapping("/list")
    public String allStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/list";
    }

    @RequestMapping("/list/{id}")
    public String detailStudent(@PathVariable ("id")long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/detail";
    }

    @RequestMapping("list/search")
    public String searchStudent(@RequestParam(name = "lastName", required=false) String lastName, Model model) {
        Student student = studentService.findByLastName(lastName);

        if (student != null) {
            model.addAttribute("student", student);
            return "student/detail";
        } else {
            return "homepage";
        }
    }

    @RequestMapping("/form")
    public String formAddStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student,Model model ) {
        if (student.getId() != null){
            studentService.update(student.getId(), student);
        } else {
            studentService.save(student);
        }
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String deleteStudent(@RequestParam("idStudent") long id) {
        Student student = studentService.findById(id);
        studentService.delete(student);
        return "redirect:/list";
    }

    @RequestMapping("/update")
    public String updateStudent(@RequestParam("idStudent") long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/form";
    }
}