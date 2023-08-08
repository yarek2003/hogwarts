package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
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


}
