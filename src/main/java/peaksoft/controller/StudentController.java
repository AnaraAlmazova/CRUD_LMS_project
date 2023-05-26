package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;
@Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }
    @ModelAttribute("groupList")
    public List<Group> getAllGroup() {
        return groupService.getAllGroups();

        }

        @GetMapping()
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/students";


    }
    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/addStudent";
}

//    @PostMapping("/saveStudent")
//    public String saveStudents(@ModelAttribute("student") Student student) {
//        studentService.addStudent(student.getGroupId(), student);
//        return "redirect:/students";
//
//    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")Student student){
        studentService.addStudent(student.getGroupId(), student);
        return "redirect:/students";
    }

    @GetMapping("update/{id}")
    public String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/updateStudent";


    }

    @PatchMapping("/{id}")
    public String saveUpdateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student) {
        studentService.updateStudent(student, id);
        return "redirect:/students";
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("id") Long id) {
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/students";

    }

}
