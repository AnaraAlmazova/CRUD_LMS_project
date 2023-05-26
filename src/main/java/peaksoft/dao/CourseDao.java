package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses();
    void addCourse(Long companyId, Course course);
    Course getCourseById(Long courseId);
    void updateCourse(Long id, Course course);

    void deleteCourse(Course course);
    List<Course> getCoursesByCompany(Long companyId);
    List<Group> getGroupsByCourse(Long courseId);

    List<Course> getCourseByGroup(Long groupId);
}
