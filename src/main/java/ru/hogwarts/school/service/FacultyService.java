package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add (Faculty faculty){
        logger.info("Faculty was created");
        return  facultyRepository.save(faculty);
    }
    public Faculty find (Long idCount) {
        logger.info("Faculty was found");
        return facultyRepository.findById(idCount).get();
    }
    public Faculty update (Faculty faculty) {
        logger.info("Faculty was changed");
        return  facultyRepository.save(faculty);
    }
    public void delete(Long idCount) {
        logger.info("Faculty was removed");
        facultyRepository.deleteById(idCount);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Faculties were found by color");
        return facultyRepository.findByColor(color);
    }
    public Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name) {
        logger.info("Faculties were found by color or name ignore case");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color,name);
    }

    public Faculty getFacultyOfStudent(long studentId) {
        logger.info("Faculty was found by student");
        return facultyRepository.findByStudents_id(studentId);

    }

}
