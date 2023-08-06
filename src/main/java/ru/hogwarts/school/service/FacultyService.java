package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long idCount = 0L;

    public Faculty add (Faculty faculty){
        faculty.setId(++idCount);
        faculties.put(idCount,faculty);
        return faculty;
    }
    public Faculty find (Long idCount) {
        if (faculties.containsKey(idCount)) {
            return faculties.get(idCount);
        } else return null;
    }
    public Faculty update (Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            return null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }
    public Faculty delete(Long idCount) {
        if (faculties.containsKey(idCount)) {
            return faculties.remove(idCount);
        }
        return null;
    }
    public Map<Long, Faculty> filterByColor(String color) {
        return faculties.entrySet().stream()
                .filter((el) -> el.getValue().getColor().equals(color))
                .collect(Collectors.toMap(Map.Entry::getKey, el -> el.getValue()));
    }
}
