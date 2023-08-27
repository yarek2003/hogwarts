package ru.hogwarts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentControllerMvcTest {

    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private FacultyService facultyService;
    @InjectMocks
    private StudentService studentService;
    @Test
    public void addStudentTest() {
        Mockito.when(studentRepository.save(new Student(1L, "Name", 1)))
                .thenReturn(new Student(1L, "Name", 1));
        Student expected = new Student(1L, "Name", 1);
        Student actual = studentService.add(new Student(1L, "Name", 1));
        assertEquals(expected, actual);
    }
    @Test
    public void findStudentTest() {
        Mockito.when(studentRepository.save(new Student(1L, "Name", 1)))
                .thenReturn(new Student(1L, "Name", 1));
        Student student = new Student(1L, "Name", 1);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        studentService.add(new Student(1L, "Name", 1));
        Optional<Student> expected = Optional.ofNullable(new Student(1L, "Name", 1));
        Optional<Student> actual = Optional.ofNullable(studentService.find(1L));
        assertEquals(expected, actual);
    }
    @Test
    public void editFacultyTest() {
        Mockito.when(studentRepository.save(new Student(1L, "Name", 1)))
                .thenReturn(new Student(1L, "Name", 1));
        studentService.add(new Student(1L, "Name", 1));
        Student expected = new Student(1L, "Name", 1);
        Student actual = studentService.update(new Student(1L, "Name", 1));
        assertEquals(expected, actual);
    }
    @Test
    public void deleteFacultyTest() {
        Mockito.when(studentRepository.save(new Student(1L, "Name", 1)))
                .thenReturn(new Student(1L, "Name", 1));
        studentService.add(new Student(1L, "Name", 1));
        studentService.delete(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
    @Test
    public void findByAgeTest() {

        Mockito.when(studentRepository.save(new Student(1L, "Name", 1))).thenReturn(new Student(1L, "Name", 1));
        Mockito.when(studentRepository.save(new Student(2L, "Name1", 2))).thenReturn(new Student(2L, "Name1", 2));
        Mockito.when(studentRepository.save(new Student(3L, "Name2", 2))).thenReturn(new Student(3L, "Name2", 2));
        Mockito.when(studentRepository.save(new Student(4L, "Name3", 4))).thenReturn(new Student(4L, "Name3", 4));

        Student student = studentService.add(new Student(1L, "Name", 1));
        Student student1 = studentService.add(new Student(2L, "Name1", 2));
        Student student2 = studentService.add(new Student(3L, "Name2", 2));
        Student student3 = studentService.add(new Student(4L, "Name3", 4));

        List<Student> expected = new ArrayList<>();
        expected.add(student1);
        expected.add(student2);

        Mockito.when(studentRepository.findByAge(2)).thenReturn(expected);

        List<Student> actual = (List<Student>) studentService.findByAge(2);

        assertEquals(expected, actual);
    }
    @Test
    void findAllByAgeBetween() {
        Mockito.when(studentRepository.save(new Student(1L, "Name", 1))).thenReturn(new Student(1L, "Name", 1));
        Mockito.when(studentRepository.save(new Student(2L, "Name1", 2))).thenReturn(new Student(2L, "Name1", 2));
        Mockito.when(studentRepository.save(new Student(3L, "Name2", 2))).thenReturn(new Student(3L, "Name2", 2));
        Mockito.when(studentRepository.save(new Student(4L, "Name3", 4))).thenReturn(new Student(4L, "Name3", 4));

        Student student = studentService.add(new Student(1L, "Name", 1));
        Student student1 = studentService.add(new Student(2L, "Name1", 2));
        Student student2 = studentService.add(new Student(3L, "Name2", 2));
        Student student3 = studentService.add(new Student(4L, "Name3", 4));


        List<Student> expected = new ArrayList<>();
        expected.add(student1);
        expected.add(student2);
        expected.add(student3);

        Mockito.when(studentRepository.findByAgeBetween(2,4)).thenReturn(expected);

        List<Student> actual = (List<Student>) studentService.findByAgeBetween(2,4);

        assertEquals(expected, actual);
    }
    @Test
    void findStudentByFaculty() {
        Mockito.when(studentRepository.save(new Student(1L, "Name", 1))).thenReturn(new Student(1L, "Name", 1));
        Mockito.when(studentRepository.save(new Student(2L, "Name1", 2))).thenReturn(new Student(2L, "Name1", 2));
        Mockito.when(studentRepository.save(new Student(3L, "Name2", 2))).thenReturn(new Student(3L, "Name2", 2));

        Student student = studentService.add(new Student(1L, "Name", 1));
        Student student1 = studentService.add(new Student(2L, "Name1", 2));
        Student student2 = studentService.add(new Student(3L, "Name2", 2));

        List<Student> expected = new ArrayList<>();
        expected.add(student);
        expected.add(student1);
        expected.add(student2);

        Mockito.when(facultyRepository.save(new Faculty(1L, "Name", "Color"))).thenReturn(new Faculty(1L, "Name", "Color"));

        Faculty faculty = facultyService.add(new Faculty(1L, "Name", "Color"));

        Mockito.when(studentRepository.findStudentsByFaculty_Id(faculty.getId())).thenReturn(expected);

        List<Student> actual = (List<Student>) studentService.findStudentsByFaculty_Id(faculty.getId());

        assertEquals(expected,actual);
    }


}
