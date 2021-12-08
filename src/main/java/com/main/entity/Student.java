package com.main.entity;



import com.main.conventers.CustomSerializable;
import com.main.builder.StudentBuilder;

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

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable, Comparable<Student>, Comparator<Student>, CustomSerializable {
    private String fullName;
    private String sex;
    private String birthDate;
    @XmlElement(name = "group")
    private Group group;
    private String numberRecordsBook;
    private boolean scholarship;

    public Student(StudentBuilder studentBuilder) {
        if(studentBuilder == null)
            throw new IllegalArgumentException("Student is NULL");
        this.fullName = studentBuilder.getFullName();
        this.sex = studentBuilder.getSex();
        this.birthDate = studentBuilder.getBirthDate();
        this.group = studentBuilder.getGroup();
        this.numberRecordsBook = studentBuilder.getNumberRecordsBook();
        this.scholarship = studentBuilder.isScholarship();
    }


    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", group=" + group +
                ", numberRecordsBook='" + numberRecordsBook + '\'' +
                ", scholarship=" + scholarship +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Group getGroup() {
        return group;
    }

    public String getNumberRecordsBook() {
        return numberRecordsBook;
    }

    public boolean isScholarship() {
        return scholarship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return scholarship == student.scholarship && fullName.equals(student.fullName) && sex.equals(student.sex) && birthDate.equals(student.birthDate) && group.equals(student.group) && numberRecordsBook.equals(student.numberRecordsBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, sex, birthDate, group, numberRecordsBook, scholarship);
    }


    @Override
    public int compare(Student student, Student t1) {
        return 0;
    }


    @Override
    public int compareTo(Student student) {
        return 0;
    }

    @Override
    public String serialize() {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Student.class);
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
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student student = (Student) unmarshaller.unmarshal(reader);
            return student;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
