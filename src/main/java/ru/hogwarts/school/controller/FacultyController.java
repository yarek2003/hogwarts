package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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

    @PutMapping
    public Faculty update (@RequestBody Faculty faculty){
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

}
