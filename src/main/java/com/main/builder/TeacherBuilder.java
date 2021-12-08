package com.main.builder;


import com.main.entity.Faculty;
import com.main.entity.Teacher;

public class TeacherBuilder {
    private String fullName;
    private String position;  // посада
    private String academicDegree;  // наукова ступінь
    private String chair;  // кафедра
    private String subject; // предмет
    private String phoneNumber;
    private String homeAddress;
    private Faculty faculty;

    public TeacherBuilder() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public TeacherBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public TeacherBuilder setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public TeacherBuilder setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
        return this;
    }

    public String getChair() {
        return chair;
    }

    public TeacherBuilder setChair(String chair) {
        this.chair = chair;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public TeacherBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TeacherBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public TeacherBuilder setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public TeacherBuilder setFaculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }

    public Teacher build(){
        Teacher teacher = null;
        if(validateTeacher()){
            teacher = new Teacher(this);
        }else
            throw new IllegalArgumentException("valid Failed");
        return teacher;
    }

    private boolean validateTeacher(){
        return ((!fullName.equals("") && fullName.length() <= 50) || (!position.equals("") && position.length() <= 10)
                || (!academicDegree.equals("") && academicDegree.length() <= 30) || (!chair.equals("") && chair.length() <= 30)
                || (!subject.equals("") && subject.length() <= 30) || (!phoneNumber.equals("") && phoneNumber.length() <= 11)
                || (!homeAddress.equals("") && homeAddress.length() <= 40) );
    }
}
