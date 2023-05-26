package peaksoft.service;

import peaksoft.entities.Course;
import peaksoft.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void addStudent(Long groupId, Student student);

    Student getStudentById(Long id);

    void updateStudent(Student student, Long id);

    void deleteStudent(Student student);

    List<Student> getStudentsByCompany(Long companyId);


    List<Student>getStudentsByTeacher(Long teacherId);


    List<Student> getStudentByName(String name);
}
