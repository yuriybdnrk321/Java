package com.main.entity;



import com.main.conventers.CustomSerializable;
import com.main.builder.FacultyBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.Objects;


@XmlRootElement(name = "faculty")
@XmlType(propOrder = {"nameFaculty","numberFaculty","countStudentInFaculty","numberPhone"})
public class Faculty implements Serializable, Comparator<Faculty>, Comparable<Faculty>, CustomSerializable {
    private String nameFaculty;
    private String numberFaculty;
    private long countStudentInFaculty;
    private String numberPhone;

    public Faculty() {
    }

    public Faculty(FacultyBuilder facultyBuilder) {
        if(facultyBuilder == null)
            throw new IllegalArgumentException("Faculty id NULL");
        this.nameFaculty = facultyBuilder.getNameFaculty();
        this.numberFaculty = facultyBuilder.getNumberFaculty();
        this.countStudentInFaculty = facultyBuilder.getCountStudentInFaculty();
        this.numberPhone = facultyBuilder.getNumberPhone();
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public String getNumberFaculty() {
        return numberFaculty;
    }

    public long getCountStudentInFaculty() {
        return countStudentInFaculty;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public void setNumberFaculty(String numberFaculty) {
        this.numberFaculty = numberFaculty;
    }

    public void setCountStudentInFaculty(long countStudentInFaculty) {
        this.countStudentInFaculty = countStudentInFaculty;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "nameFaculty='" + nameFaculty + '\'' +
                ", numberFaculty='" + numberFaculty + '\'' +
                ", countStudentInFaculty=" + countStudentInFaculty +
                ", numberPhone='" + numberPhone + '\'' +
                '}';
    }

    @Override
    public int compare(Faculty faculty, Faculty t1) {
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return countStudentInFaculty == faculty.countStudentInFaculty && Objects.equals(nameFaculty, faculty.nameFaculty) && Objects.equals(numberFaculty, faculty.numberFaculty) && Objects.equals(numberPhone, faculty.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameFaculty, numberFaculty, countStudentInFaculty, numberPhone);
    }

    @Override
    public int compareTo(Faculty faculty) {
        return -1;
    }

    @Override
    public String serialize() {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Faculty.class);
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
            JAXBContext context = JAXBContext.newInstance(Faculty.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Faculty faculty = (Faculty) unmarshaller.unmarshal(reader);
            return faculty;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
