package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.GroupDao;
import peaksoft.dao.StudentDao;

import peaksoft.entities.Group;
import peaksoft.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager manager;

    private final GroupDao groupDao;

    public StudentDaoImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student>students = manager.createQuery("From Student", Student.class).getResultList();
        return students;

    }

    @Override
    public void addStudent(Long groupId, Student student) {
        Group group= groupDao.getGroupById(groupId);
        student.setGroup(group);
        manager.persist(student);
    }


    @Override
    public Student getStudentById(Long studentId) {
        return manager.find(Student.class, studentId);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        manager.merge(student1);
    }

    @Override
    public void deleteStudent(Student student) {
        manager.remove(manager.contains(student) ? student : manager.merge(student));
    }

    @Override
    public List<Student> getStudentsByCompany(Long companyId) {
       List<Student>students=manager.createQuery(" select s from Student s join Group g on s.group.id=g.id join  g.courses c  join Company com on com.id=c.company.id where com.id=?1",Student.class)
                .setParameter(1,companyId).getResultList();
       return students;
    }

    @Override
    public List<Student> getStudentsByTeacher(Long teacherId) {
        List<Student>students=manager.createQuery("select s from Student s join Group g on s.group.id=g.id join  g.courses c  join Teacher t on t.id=c.teacher.id where t.id=?1",Student.class)
                .setParameter(1,teacherId).getResultList();
        return students;
    }

    @Override
    public List<Student> getStudentByName(String name) {
        List<Student>students=manager.createQuery("select  c from Student c where c.firstName=?1")
                .setParameter(1, name).getResultList();
        return students;

    }


}
