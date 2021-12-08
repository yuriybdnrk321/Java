package com.main.builder;


import com.main.entity.Group;
import com.main.entity.Lesson;
import com.main.entity.Teacher;

public class LessonBuilder {
    private String nameLesson;
    private String timeStart;
    private String numberClass;
    private String typeLesson;
    private Group group;
    private Teacher teacher;

    public LessonBuilder() {
        super();
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public LessonBuilder setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
        return this;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public LessonBuilder setTimeStart(String timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public String getNumberClass() {
        return numberClass;
    }

    public LessonBuilder setNumberClass(String numberClass) {
        this.numberClass = numberClass;
        return this;
    }

    public String getTypeLesson() {
        return typeLesson;
    }

    public LessonBuilder setTypeLesson(String typeLesson) {
        this.typeLesson = typeLesson;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public LessonBuilder setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public LessonBuilder setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public Lesson build(){
        Lesson lesson = null;
        if(validateLesson())
            lesson = new Lesson(this);
        else
            throw new IllegalArgumentException("valid Failed");
            return lesson;
    }

    public boolean validateLesson(){
        return ((!nameLesson.equals("") && nameLesson.length() <= 50) || (!timeStart.equals("") && timeStart.length() <= 20)
                || (!numberClass.equals("") && numberClass.length() <= 30) || (!typeLesson.equals("") && typeLesson.length() <= 30) );
    }
}
