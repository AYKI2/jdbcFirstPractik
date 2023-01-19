package firstPractic.dao;

import firstPractic.enums.Gender;
import firstPractic.model.Student;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface StudentDao {
    void createTable();
    void saveStudent(Student student);
    Student findByStudentId(Long studentId);
    void updateStudent(Long studentId, Student newStudent);
    void deleteStudent(Long studentId);
    List<Student> getAllStudents();
    List<Student> getAllStudentsSortByAge(String ascOrDesc,String type);

    void checkByAge();

    void addColumnGender(String column,String type);

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents(); //ddl


}
