package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class FacultyController {


    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty find (@PathVariable Long id){
        return facultyService.find(id);
    }
    @PostMapping
    public Faculty add (Faculty faculty){
        return facultyService.add(faculty);
    }

    @PutMapping
    public Faculty update (@RequestBody Faculty faculty){
        return facultyService.update(faculty);
    }
    @DeleteMapping("{id}")
    public Faculty delete (@PathVariable Long id){
        return facultyService.delete(id);
    }
    @GetMapping("/filter")
    public Map<Long, Faculty> filterByColor(@RequestParam("color") String color) {
        return facultyService.filterByColor(color);
    }
}
