package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.GroupDao;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public void addGroup(Long courseId, Group group) {
        Course course = manager.find(Course.class, courseId);
        List<Course>courses = new ArrayList<>();
        courses.add(course);
        List<Group> groups= new ArrayList<>();
        groups.add(group);
        course.setGroups(groups);
        group.setCourses(courses);
        manager.persist(group);
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group>groups = manager.createQuery("From Group ", Group.class).getResultList();
        return groups;

    }

    @Override
    public Group getGroupById(Long groupId) {
        Group group= manager.find(Group.class, groupId);
        return group;

    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = getGroupById(id);
       group1.setGroupName(group.getGroupName());
       group1.setDateOfStart(group.getDateOfStart());
       group1.setDateOfFinish(group.getDateOfFinish());
       manager.merge(group1);

    }

    @Override
    public void deleteGroup(Group group) {
        manager.remove(manager.contains(group)? group : manager.merge(group));
    }

    @Override
    public List<Course> getCoursesByGroup(Long groupId) {
        List<Course>courses=manager.createQuery("select c from  Course c join c.groups g where g.id=?1",Course.class)
                .setParameter(1,groupId).getResultList();
        return courses;


    }

    @Override
    public List<Student> getStudentByName(String name) {
        List<Student>students=manager.createQuery("select  c from Student c where c.firstName=?1")
                .setParameter(1, name).getResultList();
        return students;

    }
}
