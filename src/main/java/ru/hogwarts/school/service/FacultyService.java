package ru.hogwarts.school.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
@Scope("singleton")
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add (Faculty faculty){
        return  facultyRepository.save(faculty);
    }
    public Faculty find (Long idCount) {
        return facultyRepository.findById(idCount).get();
    }
    public Faculty update (Faculty faculty) {
        return  facultyRepository.save(faculty);
    }
    public void delete(Long idCount) {
        facultyRepository.deleteById(idCount);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }
    public Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color,name);
    }

    public Faculty findByStudent(Long id) {
        return facultyRepository.findByStudents_id(id);
    }

}
