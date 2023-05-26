package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.CourseDao;
import peaksoft.dao.TeacherDao;
import peaksoft.entities.Course;
import peaksoft.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Teacher> getAllTeachers() {
     //   return teacherDao.getAllTeachers();
        List<Teacher>teachers = manager.createQuery("From Teacher ", Teacher.class).getResultList();
        return teachers;

    }

    @Override
    public void addTeacher(Long courseId, Teacher teacher) {
    Course course= manager.find(Course.class, courseId);
    teacher.setCourse(course);
    manager.persist(teacher);

    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
     Teacher teacher= manager.find(Teacher.class, teacherId);
     return teacher;

    }

    @Override
    public void updateTeacher(Long id, Teacher teacher) {
    Teacher teacher1= getTeacherById(id);
    teacher1.setFirstName(teacher.getFirstName());
    teacher1.setLastName(teacher.getLastName());
    teacher1.setEmail(teacher.getEmail());
    manager.merge(teacher1);


    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        manager.remove(manager.contains(teacher) ? teacher : manager.merge(teacher));

    }
}
