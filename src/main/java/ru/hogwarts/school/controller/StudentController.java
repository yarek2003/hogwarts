package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
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
    public ResponseEntity delete (@PathVariable Long id){
         studentService.delete(id);
         return ResponseEntity.ok().build();
    }
    @GetMapping("/findByAge")
    public ResponseEntity<Collection<Student>> findByAge(@RequestParam("age") int age) {

        return ResponseEntity.ok(studentService.findByAge(age));
    }
    @GetMapping("/findByAgeBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam("minAge") int minAge,
                                                                @RequestParam("maxAge") int maxAge) {

        return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
    }
    @GetMapping("/findStudentsByFaculty")
    public Collection<Student> findStudentsByFaculty_Id(@RequestParam Long id){
        return studentService.findStudentsByFaculty_Id(id);
    }
}
