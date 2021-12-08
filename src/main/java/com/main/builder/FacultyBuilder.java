package com.main.builder;



import com.main.entity.Faculty;

import java.util.Objects;

/**
 * The type Faculty builder.
 */
public class FacultyBuilder {
    private String nameFaculty;
    private String numberFaculty;
    private long countStudentInFaculty;
    private String numberPhone;

    /**
     * Instantiates a new Faculty builder.
     */
    public FacultyBuilder() {
        super();
    }

    /**
     * Gets name faculty.
     *
     * @return the name faculty
     */
    public String getNameFaculty() {
        return nameFaculty;
    }

    /**
     * Sets name faculty.
     *
     * @param nameFaculty the name faculty
     */
    public FacultyBuilder setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
        return this;
    }

    /**
     * Gets number faculty.
     *
     * @return the number faculty
     */
    public String getNumberFaculty() {
        return numberFaculty;
    }

    /**
     * Sets number faculty.
     *
     * @param numberFaculty the number faculty
     */
    public FacultyBuilder setNumberFaculty(String numberFaculty) {
        this.numberFaculty = numberFaculty;
        return this;
    }

    /**
     * Gets count student in faculty.
     *
     * @return the count student in faculty
     */
    public long getCountStudentInFaculty() {
        return countStudentInFaculty;
    }

    /**
     * Sets count student in faculty.
     *
     * @param countStudentInFaculty the count student in faculty
     */
    public FacultyBuilder setCountStudentInFaculty(long countStudentInFaculty) {
        this.countStudentInFaculty = countStudentInFaculty;
        return this;
    }

    /**
     * Gets number phone.
     *
     * @return the number phone
     */
    public String getNumberPhone() {
        return numberPhone;
    }

    /**
     * Sets number phone.
     *
     * @param numberPhone the number phone
     */
    public FacultyBuilder setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
        return this;
    }

    /**
     * Build faculty.
     *
     * @return the faculty
     */
    public Faculty build(){
        Faculty faculty = null;
        if(validateGroup()){
            faculty = new Faculty(this);
        }else
           throw new IllegalArgumentException("valid Failed");
        return faculty;
    }

    private boolean validateGroup(){
        return ((!nameFaculty.equals("") && nameFaculty.length() <= 50) || (!numberFaculty.equals("") && numberFaculty.length() <= 20)
                || (!numberPhone.equals("") && numberPhone.length() <= 30));
    }


}
