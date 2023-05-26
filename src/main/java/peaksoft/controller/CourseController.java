package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final GroupService groupService;
    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService, GroupService groupService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.groupService = groupService;
    }
    @ModelAttribute("companyList")
    public List<Company> getCompanyList(){
        return companyService.getAllCompanies();
    }
    @GetMapping
    public String getAllCourses(Model model){
        List<Course>courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/courses";
    }
    @GetMapping("/addCourse")
    public String addCourses(Model model) {
        model.addAttribute("course", new Course());
        return "course/addCourse";
    }
    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course.getCompanyId(), course);
        return "redirect:/courses";
    }
    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);

        return "course/updateCourse";

    }
    @PatchMapping("/{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }
//    @RequestMapping("/{courseId}/{companyId}")
//    public String deleteCourse(@PathVariable("courseId") Long id,
//                               @PathVariable("companyId") Long companyId) {
//        courseService.getCourseById(id);
//        return "redirect:/courses/getCourses/ " + companyId;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        Course course = courseService.getCourseById(id);
//        courseService.deleteCourse(course);
//        return "redirect:/courses";
//    }
    @DeleteMapping("/delete")
    public String deleteCourse(@RequestParam("id") Long id) {
        Course course = courseService.getCourseById(id);
        courseService.deleteCourse(course);
        return "redirect:/courses";

    }


    @GetMapping("/groups/{courseId}")
    public String getGroupByCourseId(@PathVariable("courseId") Long courseId, Model model) {
        List<Group> groups =courseService.getGroupByCourse(courseId);
        model.addAttribute("groups", groups);
        model.addAttribute("count", groups.size());
        return "course/groups";
    }


}
