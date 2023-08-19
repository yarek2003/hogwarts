package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add (Student student){
        return  studentRepository.save(student);
    }
    public Student find (Long idCount) {
      return studentRepository.findById(idCount).get();
    }
  public Student update (Student student) {
      return  studentRepository.save(student);
  }
  public void delete(Long idCount) {
    studentRepository.deleteById(idCount);
  }

    public Collection<Student> findByAge(int age) {
      return studentRepository.findByAge(age);
    }
    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }
    public Collection<Student> findStudentsByFaculty_Id(Long id){
        return studentRepository.findStudentsByFaculty_Id(id);
    }
}

