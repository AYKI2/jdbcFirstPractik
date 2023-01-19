package firstPractic.dao;

import firstPractic.config.Util;
import firstPractic.enums.Gender;
import firstPractic.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentDao{
    private Connection connection;
    public StudentDaoImpl(){
        this.connection = Util.getConnection();
    }
    public void createTable() {
        String query = """
                CREATE TABLE if not exists students(
                id SERIAL PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                age SMALLINT NOT NULL
                );
                """;
        try (Statement statement = connection.createStatement();){
            statement.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveStudent(Student student) {
        String query = """
                INSERT INTO students(name,age) VALUES(?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Student findByStudentId(Long studentId) {
        String query = "SELECT * FROM students WHERE id = ?";
        Student student = new Student();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getByte("age"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return student;
    }

    public void updateStudent(Long studentId, Student newStudent) {
        String query = "UPDATE students SET name = ?, age = ?, gender = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setByte(2, newStudent.getAge());
            preparedStatement.setLong(3, studentId);
            preparedStatement.setObject(4,newStudent.getGender());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent(Long studentId) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,studentId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        String query = "SELECT * FROM students";
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                studentList.add(new Student(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getByte("age")));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc, String type) {
        String query = ascOrDesc;
        String aOrD = type;
        if (type.equals("asc")) {
            if (ascOrDesc.equals("name")) {
                query = """
                SELECT * FROM students ORDER BY name;
                """;
            }else if (ascOrDesc.equals("age")){
            query = """
                SELECT * FROM students ORDER BY age;
                """;
            }
        }else if(type.equals("desc")){
            if (ascOrDesc.equals("name")) {
                query = """
                SELECT * FROM students ORDER BY name desc ;
                """;
            }else if (ascOrDesc.equals("age")){
                query = """
                SELECT * FROM students ORDER BY age desc ;
                """;
            }
        }
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                studentList.add(new Student(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getByte("age")));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    @Override
    public void checkByAge() {
        String sql = """
                SELECT *, CASE WHEN age > 18 THEN 'True' ELSE 'False' END FROM students;
                """;
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println("-------------");
                System.out.println("id: "+resultSet.getLong("id"));
                System.out.println("name: "+resultSet.getString("name"));
                System.out.println("age: "+resultSet.getByte("age"));
                System.out.println("gender: "+resultSet.getByte("gender"));
                System.out.println("Check (age > 18): "+resultSet.getBoolean("case"));
                System.out.println("-------------");
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addColumnGender(String column,String type) {
        String query = """
                ALTER TABLE students ADD COLUMN ? ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,column);
            preparedStatement.setString(2,type);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
        String query = """
                SELECT gender, name, age FROM students GROUP BY gender,name,age;
                """;
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Student s1 = new Student(resultSet.getLong(1),
                        resultSet.getString(2),resultSet.getByte(3), (Gender) resultSet.getObject(4));
                students.add(s1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students.stream().collect(Collectors.groupingBy(Student::getGender));
    }

    @Override
    public void deleteAllStudents() {
        String query = """
                truncate students;
                """;
        try (Statement statement = connection.createStatement()){
            boolean cleared = statement.execute(query);
            if(cleared){
                System.out.println("Successfully cleared!");
            }else {
                System.out.println("Cleared error!");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
