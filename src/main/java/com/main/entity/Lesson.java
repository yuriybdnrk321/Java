package com.main.entity;



import com.main.conventers.CustomSerializable;
import com.main.builder.LessonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.Objects;

@XmlRootElement(name = "lesson")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lesson implements Serializable , Comparable<Lesson>, Comparator<Lesson>, CustomSerializable {
    private String nameLesson;
    private String timeStart;
    private String numberClass;
    private String typeLesson;
    @XmlElement(name = "group")
    private Group group;
    @XmlElement(name = "teacher")
    private Teacher teacher;

    public Lesson(LessonBuilder lessonBuilder) {
        if(lessonBuilder == null)
            throw new IllegalArgumentException("LESOON IS NULL");
        this.nameLesson = lessonBuilder.getNameLesson();
        this.timeStart = lessonBuilder.getTimeStart();
        this.numberClass = lessonBuilder.getTimeStart();
        this.typeLesson = lessonBuilder.getTypeLesson();
        this.group = lessonBuilder.getGroup();
        this.teacher = lessonBuilder.getTeacher();
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getNumberClass() {
        return numberClass;
    }

    public String getTypeLesson() {
        return typeLesson;
    }

    public Group getGroup() {
        return group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "nameLesson='" + nameLesson + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", numberClass='" + numberClass + '\'' +
                ", typeLesson='" + typeLesson + '\'' +
                ", group=" + group +
                ", teacher=" + teacher +
                '}';
    }

    @Override
    public int compare(Lesson lesson, Lesson t1) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return nameLesson.equals(lesson.nameLesson) && timeStart.equals(lesson.timeStart) && numberClass.equals(lesson.numberClass) && typeLesson.equals(lesson.typeLesson) && group.equals(lesson.group) && teacher.equals(lesson.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameLesson, timeStart, numberClass, typeLesson, group, teacher);
    }

    @Override
    public int compareTo(Lesson lesson) {
        return 0;
    }

    @Override
    public String serialize() {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Lesson.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(this,writer);
            String result = writer.toString();
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object deserialize(String data) {
        try {
            StringReader reader = new StringReader(data);
            JAXBContext context = JAXBContext.newInstance(Lesson.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Lesson lesson = (Lesson) unmarshaller.unmarshal(reader);
            return lesson;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
