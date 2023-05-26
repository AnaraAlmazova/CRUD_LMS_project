package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public CompanyController(CompanyService companyService, CourseService courseService, StudentService studentService) {
        this.companyService = companyService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "company/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/addCompany";

    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/update/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("updateCompany", company);
        return "company/updateCompany";
    }

    @PatchMapping("{id}")
    public String saveUpdateCompany(@PathVariable("id") Long id, @ModelAttribute("company") Company company) {
        companyService.updateCompany(id, company);
        return "redirect:/companies";
    }

    @DeleteMapping("/delete")
    public String deleteCompany(@RequestParam("id") Long id) {
        Company company = companyService.getCompanyById(id);
        companyService.deleteCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/courses/{companyId}")
    public String getCourseByCompanyId(@PathVariable("companyId") Long companyId, Model model) {
        List<Course> courseList = courseService.getCoursesByCompany(companyId);
        model.addAttribute("courseList", courseList);
        model.addAttribute("count", courseList.size());   //kancha kurs bar ili student
        return "company/courses";
    }

//    @GetMapping("/students/{companyId}")
//    public String getStudentsByTeacher(Model model, @PathVariable("companyId") Long companyId) {
//        List<Student> students = studentService.getStudentsByCompany(companyId);
//        model.addAttribute("size", students.size());
//        model.addAttribute("students", students);
//        return "company/getStudents";
//
//
//    }
    @GetMapping("/students/{companyId}")
    public String getStudentsByCompany(Model model, @PathVariable("companyId") Long companyId) {
        List<Student> students = studentService.getStudentsByCompany(companyId);
        model.addAttribute("size", students.size());
        model.addAttribute("students", students);
        return "company/getStudents";


    }

}

