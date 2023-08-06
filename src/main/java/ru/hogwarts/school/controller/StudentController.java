package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {

  private final    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public Student find (@PathVariable Long id){
        return studentService.find(id);
    }
    @PostMapping
    public Student add (Student student){
        return studentService.add(student);
    }

    @PutMapping
    public Student update (@RequestBody Student student){
        return studentService.update(student);
    }
    @DeleteMapping("{id}")
    public Student delete (@PathVariable Long id){
        return studentService.delete(id);
    }
    @GetMapping("/filter")
    public Map<Long, Student> filterByAge(@RequestParam("age") int age) {
        return studentService.filterByAge(age);
    }
}
