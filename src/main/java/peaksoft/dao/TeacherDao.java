package peaksoft.dao;


import peaksoft.entities.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getAllTeachers();

    void addTeacher(Long courseId, Teacher teacher);

    Teacher getTeacherById(Long teacherId);

    void updateTeacher(Long id, Teacher teacher);

    void deleteTeacher(Teacher teacher);
}





