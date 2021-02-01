package project.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {

        Optional<Student> student = studentRepository.findById(id);

        return student.get();
    }

    public void addStudent(Student student) {

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Optional<Student> student = studentRepository.findById(id);

        if (name != null) {
            student.get().setName(name);
        }

        if (email != null) {
            student.get().setEmail(email);
        }

    }


    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("there's no student with this ID");
        }
        studentRepository.deleteById(id);
    }
}