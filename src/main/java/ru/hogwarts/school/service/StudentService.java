package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add (Student student){
        logger.info("Student was created");
        return  studentRepository.save(student);
    }
    public Student find (Long idCount) {
      logger.info("Student was found");
      return studentRepository.findById(idCount).get();
    }
  public Student update (Student student) {
      logger.info("Student was changed");
      return  studentRepository.save(student);
  }
  public void delete(Long idCount) {
     logger.info("Student was removed");
     studentRepository.deleteById(idCount);
  }

    public Collection<Student> findByAge(int age) {
      logger.info("Students were found by age");
      return studentRepository.findByAge(age);
    }
    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Students were found by age between");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }
    public Collection<Student> findStudentsByFaculty_Id(Long id){
        logger.info("Students were found by faculty");
        return studentRepository.findStudentsByFaculty_Id(id);
    }

    public int countStudents() {
        logger.info("Students were counted");
        return studentRepository.countStudents();
    }

    public int averageAgeOfStudents() {
        logger.info("Average age was calculated");
        return studentRepository.averageAgeOfStudents();
    }

    public List<Student> last5Students() {
        logger.info("Last 5 students were found");
        return studentRepository.last5Students();
    }

    public List<String> studentsA() {
        logger.info("Getting students name starting with A");
        return studentRepository.findAll()
                .stream()
                .parallel()
                .map(el -> el.getName().toUpperCase())
                .filter(el -> el.startsWith("A"))
                .sorted()
                .toList();
    }
    public Double averageAge() {
        logger.info("Getting students average age");
        return studentRepository.findAll()
                .stream()
                .parallel()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }
    public Integer testSpeedStream1() {
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(100_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        return sum;
    }

    public Integer testSpeedStream2() {
        int sum = IntStream.iterate(1, a -> a + 1)
                .limit(100_000_000)
                .reduce(0, (a, b) -> a + b);
        return sum;
    }
}


