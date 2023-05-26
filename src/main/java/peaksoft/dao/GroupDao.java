package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;

import java.util.List;

public interface GroupDao {
    void addGroup(Long courseId, Group group);

    List<Group> getAllGroups();
    Group getGroupById(Long groupId);
    void updateGroup(Long id, Group group);
    void deleteGroup(Group group);
    List<Course>getCoursesByGroup(Long groupId);
    List<Student>getStudentByName(String name);
    }


