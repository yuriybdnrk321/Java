package com.main.builder;


import com.main.entity.Faculty;
import com.main.entity.Group;

public class GroupBuilder {
    protected String number;
    protected String course;
    protected String direction;
    protected Faculty faculty;

    public GroupBuilder(){
        super();
    }

    public GroupBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public GroupBuilder setCourse(String course) {
        this.course = course;
        return this;
    }

    public GroupBuilder setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public GroupBuilder setFaculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public String getCourse() {
        return course;
    }

    public String getDirection() {
        return direction;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Group build(){
        Group group = null;
        if(validateGroup()){
            group = new Group(this);
        }else
            throw new IllegalArgumentException("valid Failed");
        return group;
    }

    private boolean validateGroup(){
        return ((!number.equals("") && number.length() <= 50) || (!course.equals("") && course.length() == 20)
                || (!direction.equals("") && direction.length() <= 30));
    }
}
