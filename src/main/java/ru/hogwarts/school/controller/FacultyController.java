package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;


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


    @PutMapping("/{id}")
    public Faculty update(@RequestBody() Faculty faculty, @PathVariable() Long id) {
        faculty.setId(id);
        return facultyService.update(faculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable Long id){
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }
   @GetMapping("/findByColor")
    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam("color") String color) {
        return ResponseEntity.ok(facultyService.findByColor(color));
    }
    @GetMapping("/findByColorIgnoreCaseOrNameIgnoreCase")
    public ResponseEntity<Collection<Faculty>> findByColorIgnoreCaseOrNameIgnoreCase(@RequestParam(required = false, name = "color") String color,
                                                                                     @RequestParam(required = false, name = "name") String name) {
        return ResponseEntity.ok(facultyService.findByColorIgnoreCaseOrNameIgnoreCase(color,name));
    }
    @GetMapping("/getFacultyOfStudent")
    public ResponseEntity<Faculty> getFacultyOfStudent(@RequestParam("studentId") long studentId) {
        return ResponseEntity.ok(facultyService.getFacultyOfStudent(studentId));
    }
}
