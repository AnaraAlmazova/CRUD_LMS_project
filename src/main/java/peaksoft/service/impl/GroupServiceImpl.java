package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.dao.GroupDao;
import peaksoft.dao.StudentDao;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.service.GroupService;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private final CourseDao courseDao;
    private final StudentDao studentDao;
    @Autowired
    public GroupServiceImpl(GroupDao groupDao, CourseDao courseDao, StudentDao studentDao) {
        this.groupDao = groupDao;
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();

    }

    @Override
    public void addGroup(Group group, Long courseId) {

        groupDao.addGroup(courseId, group);

    }

    @Override
    public Group getGroupById(Long groupId) {
        return groupDao.getGroupById(groupId);

        }

    @Override
    public void updateGroup(Long id, Group group) {
        groupDao.updateGroup(id, group);


    }

    @Override
    public void deleteGroup(Group group) {
        groupDao.deleteGroup(group);

    }

    @Override
        public List<Course> getCoursesByGroup(Long groupId) {
            return groupDao.getCoursesByGroup(groupId);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentDao.getStudentByName(name);
    }
}
