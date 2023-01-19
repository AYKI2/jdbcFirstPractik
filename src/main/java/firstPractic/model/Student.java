package firstPractic.model;

import firstPractic.enums.Gender;

public class Student {
    private Long id;
    private String name;
    private Byte age;
    private Gender gender;
    public Student(){}
    public Student(String name, Byte age){
        this.name = name;
        this.age = age;
    }
    public Student(Long id, String name, Byte age,Gender gender){
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student(long id, String name, byte age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "\nStudent:" +
                "\nid = " + id +
                "\nname = " + name +
                "\nage = " + age;
    }
}
