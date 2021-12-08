package com.main.entity;


import com.main.conventers.CustomSerializable;
import com.main.builder.TeacherBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Comparator;

@XmlRootElement(name = "teacher")
@XmlAccessorType(XmlAccessType.FIELD)
public class Teacher implements Serializable, Comparable<Teacher>, Comparator<Teacher>, CustomSerializable {
    String fullName;
    String position;
    String academicDegree;
    String chair;
    String subject;
    String phoneNumber;
    String homeAddress;
    @XmlElement(name = "faculty")
    Faculty faculty;



    public Teacher(TeacherBuilder teacherBuilder) {
        if(teacherBuilder == null)
            throw new IllegalArgumentException("teacher is NULL");
        this.fullName = teacherBuilder.getFullName();
        this.position = teacherBuilder.getPosition();
        this.academicDegree = teacherBuilder.getAcademicDegree();
        this.chair = teacherBuilder.getChair();
        this.subject = teacherBuilder.getSubject();
        this.phoneNumber = teacherBuilder.getPhoneNumber();
        this.homeAddress = teacherBuilder.getHomeAddress();
        this.faculty = teacherBuilder.getFaculty();
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public String getChair() {
        return chair;
    }

    public String getSubject() {
        return subject;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", academicDegree='" + academicDegree + '\'' +
                ", chair='" + chair + '\'' +
                ", subject='" + subject + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", faculty=" + faculty +
                '}';
    }


    @Override
    public int compareTo(Teacher teacher) {
        return 0;
    }

    @Override
    public int compare(Teacher teacher, Teacher t1) {
        return 0;
    }

    @Override
    public String serialize() {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Teacher.class);
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
            JAXBContext context = JAXBContext.newInstance(Teacher.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Teacher teacher = (Teacher) unmarshaller.unmarshal(reader);
            return teacher;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
