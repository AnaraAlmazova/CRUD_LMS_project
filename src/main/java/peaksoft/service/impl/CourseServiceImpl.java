package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.dao.GroupDao;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.CourseService;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;
    private final GroupDao groupDao;
    @Autowired
    public CourseServiceImpl(CourseDao courseDao, GroupDao groupDao) {
        this.courseDao = courseDao;
        this.groupDao = groupDao;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void addCourse(Long companyId, Course course) {
        courseDao.addCourse(companyId, course);

    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        courseDao.updateCourse(id, course);

    }

    @Override
    public void deleteCourse(Course course) {
        courseDao.deleteCourse(course);

    }

    @Override
    public List<Course> getCoursesByCompany(Long companyId) {

        return courseDao.getCoursesByCompany(companyId);

    }

    @Override
        public List<Group> getGroupByCourse(Long courseId) {
            return courseDao.getGroupsByCourse(courseId);


    }

    @Override
    public List<Course> getCoursesByGroup(Long groupId) {
        return courseDao.getCourseByGroup(groupId);
    }


}

