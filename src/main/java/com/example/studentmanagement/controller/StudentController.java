
package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

// Dashboard
@GetMapping("/dashboard")
public String dashboard(Model model) {

    System.out.println("### DASHBOARD CONTROLLER EXECUTED ###");

    List<Student> students = studentService.getAllStudents();

    model.addAttribute("totalStudents", students.size());
    model.addAttribute("recentStudents", students);

    return "dashboard";
}
    // Display all students
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // Show add student form
    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add Student");
        model.addAttribute("formAction", "/students");
        return "create_student";
    }

    // Save student
    @PostMapping("/students")
    public String saveStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Add Student");
            model.addAttribute("formAction", "/students");
            return "create_student";
        }

        studentService.saveStudent(student);

        redirectAttributes.addFlashAttribute(
                "successMessage", "Student saved successfully.");

        return "redirect:/students";
    }

    // Edit student
    @GetMapping("/students/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        return studentService.getStudentById(id)
                .map(student -> {
                    model.addAttribute("student", student);
                    model.addAttribute("pageTitle", "Edit Student");
                    model.addAttribute("formAction",
                            "/students/" + id + "/update");
                    return "create_student";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute(
                            "errorMessage", "Student not found.");
                    return "redirect:/students";
                });
    }

    // Update student
    @PostMapping("/students/{id}/update")
    public String updateStudent(
            @PathVariable Long id,
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            student.setId(id);
            model.addAttribute("pageTitle", "Edit Student");
            model.addAttribute("formAction",
                    "/students/" + id + "/update");
            return "create_student";
        }

        student.setId(id);
        studentService.saveStudent(student);

        redirectAttributes.addFlashAttribute(
                "successMessage", "Student updated successfully.");

        return "redirect:/students";
    }

    // Delete student
    @PostMapping("/students/{id}/delete")
    public String deleteStudent(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (studentService.getStudentById(id).isPresent()) {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage", "Student deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Student not found.");
        }

        return "redirect:/students";
    }
}
