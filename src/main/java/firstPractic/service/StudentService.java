package firstPractic.service;

import firstPractic.enums.Gender;
import firstPractic.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    String createTable();
    String saveStudent(Student student);
    Student findByStudentId(Long studentId);
    String updateStudent(Long studentId, Student newStudent);
    String deleteStudent(Long studentId);
    List<Student> getAllStudents();
    List<Student> getAllStudentsSortByAge(String ascOrDesc,String type);

    void checkByAge();

    void addColumnGender(String column,String type);

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents(); //ddl
}
