package firstPractic.service;

import firstPractic.dao.StudentDao;
import firstPractic.dao.StudentDaoImpl;
import firstPractic.enums.Gender;
import firstPractic.model.Student;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService{
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public String createTable() {
        studentDao.createTable();
        return "Table created successfully!";
    }

    @Override
    public String saveStudent(Student student) {
        studentDao.saveStudent(student);
        return "Successfully saved!";
    }

    @Override
    public Student findByStudentId(Long studentId) {
        return studentDao.findByStudentId(studentId);
    }

    @Override
    public String updateStudent(Long studentId, Student newStudent) {
        studentDao.updateStudent(studentId,newStudent);
        return "Successfully updated!";
    }

    @Override
    public String deleteStudent(Long studentId) {
        studentDao.deleteStudent(studentId);
        return "Successfully deleted!";
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc,String type) {
        return studentDao.getAllStudentsSortByAge(ascOrDesc,type);
    }

    @Override
    public void checkByAge() {
        studentDao.checkByAge();
    }

    @Override
    public void addColumnGender(String column, String type) {
        studentDao.addColumnGender(column,type);
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
        return studentDao.gruopByGender();
    }

    @Override
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();
    }
}
