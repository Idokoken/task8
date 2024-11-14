package ndgroups.task8.controller;

import ndgroups.task8.model.Student;
import ndgroups.task8.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneStudents(@PathVariable Integer id) {
        Student student = studentService.getOneStudent(id);
        if (student  == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Student> createStudents(@RequestBody Student student) {
        Student newStudent =  studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student editStudent  = studentService.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.CREATED).body(editStudent);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
