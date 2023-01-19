package firstPractic;

import firstPractic.model.Student;
import firstPractic.service.StudentService;
import firstPractic.service.StudentServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StudentService studentService = new StudentServiceImpl();
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("""
                    \n
                    1 - Create Table,
                    2 - Save student,
                    3 - Find by id,
                    4 - Update student,
                    5 - Delete student,
                    6 - Show all student,
                    7 - Sort,
                    8 - Check age,
                    9 - Create table,
                    10 - Group by gender,
                    11 - Delete all students,
                    12 - exit;
                    """);
            int choose = new Scanner(System.in).nextInt();
            switch (choose) {
                case 1:
                    System.out.println(studentService.createTable());
                    break;
                case 2:
                    System.out.println("Enter name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println("Enter age: ");
                    byte age = new Scanner(System.in).nextByte();
                    Student student = new Student(name, age);
                    System.out.println(studentService.saveStudent(student));
                    break;
                case 3:
                    System.out.println("Enter id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(studentService.findByStudentId(id));
                    break;
                case 4:
                    System.out.println("Enter id: ");
                    long updateId = new Scanner(System.in).nextLong();
                    System.out.println("Enter name: ");
                    String newName = new Scanner(System.in).nextLine();
                    System.out.println("Enter age: ");
                    byte newAge = new Scanner(System.in).nextByte();
                    Student updateStudent = new Student(newName,newAge);
                    System.out.println(studentService.updateStudent(updateId, updateStudent));
                    break;
                case 5:
                    System.out.println("Enter id: ");
                    long studentId = new Scanner(System.in).nextLong();
                    System.out.println(studentService.deleteStudent(studentId));
                    break;
                case 6:
                    System.out.println(studentService.getAllStudents());
                    break;
                case 7:
                    System.out.println("Asc or Desc: ");
                    String type = new Scanner(System.in).next();
                    System.out.println("Sort by: ");
                    System.out.println(studentService.getAllStudentsSortByAge(new Scanner(System.in).nextLine(),type));
                    break;
                case 8:
                    studentService.checkByAge();
                    break;
                case 9:
                    studentService.createTable();
                    break;
                case 10:
                    studentService.gruopByGender();
                    break;
                case 11:
                    studentService.deleteAllStudents();
                    break;
                case 12:
                    isTrue = false;
                    break;
            }
        }
    }
}
