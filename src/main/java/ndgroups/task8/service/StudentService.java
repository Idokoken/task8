package ndgroups.task8.service;

import jakarta.persistence.EntityNotFoundException;
import ndgroups.task8.model.Student;
import ndgroups.task8.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student)  {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents()  {
        return studentRepository.findAll();
    }
    public Student getOneStudent(Integer id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Integer id, Student student) {
        Student existingStudent  = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("student not found"));

        // Copy updated fields to existing product (excluding id)
        BeanUtils.copyProperties(student,existingStudent, "id");

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
