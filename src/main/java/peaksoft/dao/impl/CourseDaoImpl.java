package peaksoft.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.CourseDao;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = manager.createQuery("From Course", Course.class).getResultList();
        return courses;
    }

    @Override
    public void addCourse(Long companyId, Course course) {
        Company company = manager.find(Company.class, companyId);
        course.setCompany(company);
        manager.persist(course);

    }

    @Override
    public Course getCourseById(Long courseId) {
        Course course = manager.find(Course.class, courseId);
        return course;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = getCourseById(id);
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        manager.merge(course1);

    }

    @Override
    public void deleteCourse(Course course) {
        manager.remove(manager.contains(course) ? course : manager.merge(course));

    }

    @Override
    public List<Course> getCoursesByCompany(Long companyId) {
        List<Course> courses = manager.createQuery("SELECT c FROM Course c join c.company com WHERE com.id=?1")
                .setParameter(1, companyId).getResultList();
        return courses;

    }

    @Override
    public List<Group> getGroupsByCourse(Long courseId) {
        List<Group> groups = manager.createQuery("select c from  Group c join c.courses g where g.id=?1", Group.class)
                .setParameter(1, courseId).getResultList();
        return groups;
    }

    @Override
    public List<Course> getCourseByGroup(Long groupId) {
        return null;
    }
}