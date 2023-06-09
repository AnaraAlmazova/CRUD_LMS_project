package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;

import peaksoft.entities.Student;
import peaksoft.entities.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.StudentService;
import peaksoft.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService, StudentService studentService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;

    }
    @ModelAttribute("courseList")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping()
    public String getAllTeacher(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/teachers";


        }

    @GetMapping("/addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/addTeacher";


    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.addTeacher(teacher.getCourseId(), teacher);
        return "redirect:/teachers";


    }

    @GetMapping("update/{id}")
    public String updateTeacher(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/updateTeacher";


        }

    @PatchMapping("/{id}")
    public String saveUpdateTeacher(@PathVariable("id") Long id, @ModelAttribute("teacher") Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return "redirect:/teachers";

    }

    @DeleteMapping("/delete")
    public String deleteTeacher(@RequestParam("id") Long id) {
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/teachers";


    }
    @GetMapping("/students/{teacherId}")
    public String getStudentsByTeacher(Model model, @PathVariable("teacherId") Long teacherId) {
        List<Student> students = studentService.getStudentsByTeacher(teacherId);
        model.addAttribute("size", students.size());
        model.addAttribute("students", students);
        return "teacher/getStudents";

    }



}
