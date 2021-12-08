package com.main;

import com.main.builder.FacultyBuilder;
import com.main.builder.GroupBuilder;
import com.main.conventers.ConverterJsonFaculty;
import com.main.conventers.ConverterJsonGroup;
import com.main.conventers.ConverterXML;
import com.main.db.ConstantQuery;
import com.main.db.MysqlConnector;
import com.main.entity.Faculty;
import com.main.entity.Group;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws JAXBException {
/*       MysqlConnector mysqlConnector = new MysqlConnector("university_new");
        boolean resultCreate = mysqlConnector.createDb();
        System.out.println("Result Create: " + resultCreate);
        int result;
        System.out.println(mysqlConnector.executeUpdateSQLQuery(ConstantQuery.CREATE__TABLE_STUDENT));
        System.out.println(mysqlConnector.executeUpdateSQLQuery(ConstantQuery.CREATE_FACULTY));
        System.out.println(mysqlConnector.executeUpdateSQLQuery(ConstantQuery.CREATE_GROUP));
        System.out.println(mysqlConnector.executeUpdateSQLQuery(ConstantQuery.CREATE_LESSON));
        System.out.println(mysqlConnector.executeUpdateSQLQuery(ConstantQuery.CREATE_TEACHER));*/

        Faculty faculty = new FacultyBuilder().setNameFaculty("FAK_1").setNumberFaculty("101").setCountStudentInFaculty(250)
                .setNumberPhone("0951234567").build();

        ArrayList<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);
        Collection collection = faculties;

        // serizlize Json Faculty
        ConverterJsonFaculty converterJsonFaculty = new ConverterJsonFaculty();
        String json = converterJsonFaculty.getJson(collection);
        WorkingWithFile.saveState(json);
        //deserialize json
        String object = (String.valueOf(WorkingWithFile.loadState()));
        List<Faculty> facultyList = converterJsonFaculty.getListJson(object);
        System.out.println(facultyList);

        Group group = new GroupBuilder().setDirection("Informatic").setCourse("3").setNumber("404").setFaculty(faculty).build();

        Group group1 = new Group();
        group1.setNumber("fgdf");
        group.setCourse("dfg");

        ArrayList<Group> groups = new ArrayList<>();
        groups.add(group);
        Collection collectionGroups = groups;

        //serialize Json
        ConverterJsonGroup converterJsonGroup = new ConverterJsonGroup();
        String jsonGroup = converterJsonGroup.getJson(collectionGroups);
        WorkingWithFile.saveState(jsonGroup);
        //deserialize json
        String objectGroup = (String.valueOf(WorkingWithFile.loadState()));
        List<Group> groupList = converterJsonGroup.getListJson(objectGroup);
        System.out.println(groupList);
        System.out.println(group);

        //serialize XML
        String xmlFaculty = faculty.serialize();
        String xmlGroup = group.serialize();
        System.out.println(xmlFaculty);
        System.out.println(xmlGroup);
        Faculty faculty1 = (Faculty) new Faculty().deserialize(xmlFaculty);
        faculty1.toString();

    }




}
