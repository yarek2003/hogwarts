package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long idCount = 0L;

    public Student add (Student student){
        student.setId(++idCount);
        students.put(idCount,student);
        return student;
    }
    public Student find (Long idCount) {
      if (students.containsKey(idCount)) {
        return students.get(idCount);
      } else return null;
    }
  public Student update (Student student) {
    if (!students.containsKey(student.getId())) {
      return null;
    }
    students.put(student.getId(), student);
    return student;
  }
  public Student delete(Long idCount) {
    if (students.containsKey(idCount)) {
      return students.remove(idCount);
    }
    return null;
  }
    public Map<Long, Student> filterByAge(int age) {
        return students.entrySet().stream()
                .filter(el -> el.getValue().getAge() == (age))
                .collect(Collectors.toMap(el -> el.getKey(), el -> el.getValue()));
    }

}

