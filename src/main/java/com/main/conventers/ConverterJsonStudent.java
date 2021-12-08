package com.main.conventers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.main.builder.FacultyBuilder;
import com.main.builder.GroupBuilder;
import com.main.builder.StudentBuilder;
import com.main.entity.Faculty;
import com.main.entity.Group;
import com.main.entity.Student;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConverterJsonStudent implements JsonSerializer<Student>, JsonDeserializer<Student> {
    Type itemsListType = new TypeToken<List<Student>>() {}.getType();

    @Override
    public JsonElement serialize(Student src, Type typeOfSrc, JsonSerializationContext context) {


        JsonObject objectStudent = new JsonObject();
        objectStudent.addProperty("fullName",src.getFullName());
        objectStudent.addProperty("birthDate",src.getBirthDate());
        objectStudent.addProperty("sex",src.getSex());
        objectStudent.addProperty("scholarship",src.isScholarship());
        objectStudent.addProperty("numberRecordsBook",src.getNumberRecordsBook());

        JsonObject objectGroup = new JsonObject();
        objectGroup.addProperty("number",src.getGroup().getNumber());
        objectGroup.addProperty("course",src.getGroup().getCourse());
        objectGroup.addProperty("direction",src.getGroup().getDirection());


        JsonObject objectFaculty = new JsonObject();
        objectFaculty.addProperty("name",src.getGroup().getFaculty().getNameFaculty());
        objectFaculty.addProperty("number",src.getGroup().getFaculty().getNumberFaculty());
        objectFaculty.addProperty("countInFaculty",src.getGroup().getFaculty().getCountStudentInFaculty());
        objectFaculty.addProperty("numberPhone",src.getGroup().getFaculty().getNumberPhone());

        JsonElement elementFaculty = objectFaculty;
        objectGroup.add("faculty",elementFaculty);

        JsonElement elementGroup = objectGroup;

        objectStudent.add("group",elementGroup);

        return objectStudent;
    }


    @Override
    public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();


        JsonObject groupObject = object.getAsJsonObject("group");
        JsonObject facultyObject = groupObject.getAsJsonObject("faculty");
        Faculty faculty = new FacultyBuilder()
                .setNameFaculty(facultyObject.get("name").getAsString())
                .setNumberFaculty(facultyObject.get("number").getAsString())
                .setCountStudentInFaculty(facultyObject.get("countInFaculty").getAsLong())
                .setNumberPhone(facultyObject.get("numberPhone").getAsString()).build();

        Group group = new GroupBuilder()
                .setCourse(groupObject.get("nameLesson").getAsString())
                .setNumber(groupObject.get("typeLesson").getAsString())
                .setDirection(groupObject.get("numberClass").getAsString())
                .setFaculty(faculty)
                .build();

        Student student = new StudentBuilder()
                .setFullName(object.get("name").getAsString())
                .setBirthDate(object.get("number").getAsString())
                .setScholarship(object.get("scholarship").getAsBoolean())
                .setSex(object.get("sex").getAsString())
                .setNumberRecordsBook(object.get("numberRecordsBook").getAsString())
                .setGroup(group).build();

        return student;
    }

    public Gson registerGsonBuilder(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, this);
        Gson gson = builder.create();
        return gson;
    }
    public String getJson(Map map){
        return registerGsonBuilder().toJson(map);
    }
    public String getJsonObjectStr(ArrayList<Student> students){
        Collection collection = students;
        return registerGsonBuilder().toJson(collection);
    }

    public List<Student> getListJson(String json){
        return registerGsonBuilder().fromJson(json,itemsListType);
    }
}
