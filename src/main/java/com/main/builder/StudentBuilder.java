package com.main.builder;


import com.main.entity.Group;
import com.main.entity.Student;

public class StudentBuilder {
    private String fullName;
    private String sex;
    private String birthDate;
    private Group group;
    private String numberRecordsBook;
    private boolean scholarship;

    public StudentBuilder() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public StudentBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public StudentBuilder setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public StudentBuilder setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public StudentBuilder setGroup(Group group) {
        this.group = group;
        return this;
    }

    public String getNumberRecordsBook() {
        return numberRecordsBook;
    }

    public StudentBuilder setNumberRecordsBook(String numberRecordsBook) {
        this.numberRecordsBook = numberRecordsBook;
        return this;
    }

    public boolean isScholarship() {
        return scholarship;
    }

    public StudentBuilder setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
        return this;
    }

    public Student build(){
        Student student = null;
        if(validateStudent()) {
           student = new Student(this);
        }else
            throw new IllegalArgumentException("valid Failed");
        return student;
    }

    public boolean validateStudent(){
            return ((!fullName.equals("") && fullName.length() <= 50) || (!sex.equals("") && sex.length() <= 10)
                    || (!birthDate.equals("") && birthDate.length() <= 30) || (!numberRecordsBook.equals("") && numberRecordsBook.length() <= 30) );
    }
}
