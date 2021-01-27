package project.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired          // Создает instance
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable("id") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }

    @DeleteMapping
    public void deleteAll() {
        studentService.deleteAllStudents();
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
    }


}

