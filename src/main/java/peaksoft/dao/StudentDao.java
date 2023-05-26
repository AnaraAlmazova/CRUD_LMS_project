package peaksoft.dao;


import peaksoft.entities.Course;
import peaksoft.entities.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();

    void addStudent(Long groupId, Student student);

    Student getStudentById(Long studentId);

    void updateStudent(Long id, Student student);

    void deleteStudent(Student student);

    List<Student> getStudentsByCompany(Long companyId);

    List<Student> getStudentsByTeacher(Long teacherId);


    List<Student> getStudentByName(String name);
}
