package com.main.entity;


import com.main.conventers.CustomSerializable;
import com.main.builder.GroupBuilder;

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


@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group implements Serializable, Comparable<Group>, Comparator<Group>, CustomSerializable {
    private String number;
    private String course;
    private String direction;
    @XmlElement(name = "faculty")
    private Faculty faculty;

    public Group() {
    }

    public Group(GroupBuilder groupBuilder){
        if(groupBuilder == null){
            throw new IllegalArgumentException("Group is NULL");
        }
        this.course = groupBuilder.getCourse();      // курс
        this.direction = groupBuilder.getDirection();  // напрям
        this.faculty = groupBuilder.getFaculty();   // факультет
        this.number = groupBuilder.getNumber();  // номер
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public int compare(Group group, Group t1) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getNumber().equals(group.getNumber()) && getCourse().equals(group.getCourse()) && getDirection().equals(group.getDirection()) && getFaculty().equals(group.getFaculty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getCourse(), getDirection(), getFaculty());
    }

    @Override
    public String toString() {
        return "Group{" +
                "number='" + number + '\'' +
                ", course='" + course + '\'' +
                ", direction='" + direction + '\'' +
                ", Faculty{" +
                "  nameFaculty='" + faculty.getNameFaculty() + '\'' +
                ", numberFaculty='" + faculty.getNumberFaculty() + '\'' +
                ", countStudentInFaculty=" + faculty.getCountStudentInFaculty() +
                ", numberPhone='" + faculty.getNumberPhone() + '\'' +
                    '}' +
                '}';
    }

    @Override
    public int compareTo(Group group) {
        return 0;
    }

    @Override
    public String serialize() {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Group.class);
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
            JAXBContext context = JAXBContext.newInstance(Group.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Group group = (Group) unmarshaller.unmarshal(reader);
            return group;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
