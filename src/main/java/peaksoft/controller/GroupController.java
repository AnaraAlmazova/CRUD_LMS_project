package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;


import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final CourseService courseService;

    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public GroupController(CourseService courseService, GroupService groupService, StudentService studentService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.studentService = studentService;
    }


    @GetMapping()
    public String getAllGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "group/groups";


    }

    @GetMapping("/addGroup")
    public String addGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/addGroup";

    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.addGroup(group, group.getCourseId());
        return "redirect:/groups";

    }

    @GetMapping("update/{id}")
    public String updateGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "group/updateGroup";

    }

    @PatchMapping("/{id}")
    public String saveUpdateGroup(@PathVariable("id") Long id, @ModelAttribute("group") Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/groups";

    }

    @DeleteMapping("/delete")
    public String deleteGroup(@RequestParam("id") Long id) {
        groupService.deleteGroup(groupService.getGroupById(id));
        return "redirect:/groups";


    }

    @GetMapping("/courses/{companyId}")
    public String getCourseByCompanyId(@PathVariable("companyId") Long companyId, Model model) {
        List<Course> courseList = courseService.getCoursesByCompany(companyId);
        model.addAttribute("courseList", courseList);
        model.addAttribute("count", courseList.size());   //kancha kurs bar ili student
        return "company/courses";
    }

    @ModelAttribute("courseList")
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{groupId}")
    public String getCourseByGroupId(@PathVariable("groupId") Long groupId, Model model) {
        List<Course> courses = courseService.getCoursesByGroup(groupId);
        model.addAttribute("courses", courses);
        model.addAttribute("count", courses.size());
        return "group/courses";
    }

    @GetMapping("/search")
    public String getStudentName(Model model, String name) {
        List<Student> students = studentService.getStudentByName(name);
        List<Student> studentList = studentService.getAllStudents();
        if (name != null) {

            model.addAttribute("students", students);
        } else {
            model.addAttribute("students", studentList);
        }
            return "group/getStudents";


        }
    }









