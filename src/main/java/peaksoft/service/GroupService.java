package peaksoft.service;

import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;

import java.util.List;

public interface GroupService {


    List<Group> getAllGroups();

    void addGroup(Group group, Long courseId);

    Group getGroupById(Long groupId);


    void updateGroup(Long id, Group group);

    void deleteGroup(Group group);
    List<Course> getCoursesByGroup(Long id);
    List<Student>getStudentByName(String name);
}



