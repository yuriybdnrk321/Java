package com.main.conventers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.main.builder.FacultyBuilder;
import com.main.builder.GroupBuilder;
import com.main.builder.TeacherBuilder;
import com.main.entity.Faculty;
import com.main.entity.Group;
import com.main.entity.Teacher;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConverterJsonTeacher implements JsonSerializer<Teacher>, JsonDeserializer<Teacher> {
    Type itemsListType = new TypeToken<List<Teacher>>() {}.getType();

    @Override
    public JsonElement serialize(Teacher src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("fullName",src.getFullName());
        object.addProperty("position",src.getPosition());
        object.addProperty("academicDegree",src.getAcademicDegree());
        object.addProperty("chair",src.getChair());
        object.addProperty("subject",src.getSubject());
        object.addProperty("phoneNumber",src.getPhoneNumber());
        object.addProperty("homeAddress",src.getHomeAddress());


        JsonObject objectFaculty = new JsonObject();
        objectFaculty.addProperty("name",src.getFaculty().getNameFaculty());
        objectFaculty.addProperty("number",src.getFaculty().getNumberFaculty());
        objectFaculty.addProperty("countInFaculty",src.getFaculty().getCountStudentInFaculty());
        objectFaculty.addProperty("numberPhone",src.getFaculty().getNumberPhone());
        JsonElement element = objectFaculty;
        object.add("faculty",element);

        return object;
    }


    @Override
    public Teacher deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();


        JsonObject facultyObject = object.getAsJsonObject("faculty");
        Faculty faculty = new FacultyBuilder()
                .setNameFaculty(facultyObject.get("name").getAsString())
                .setNumberFaculty(facultyObject.get("number").getAsString())
                .setCountStudentInFaculty(facultyObject.get("countInFaculty").getAsLong())
                .setNumberPhone(facultyObject.get("numberPhone").getAsString()).build();


        Teacher teacher = new TeacherBuilder().setFaculty(faculty)
                .setFullName(object.get("fullName").getAsString())
                .setPosition(object.get("position").getAsString())
                .setAcademicDegree(object.get("academicDegree").getAsString())
                .setChair(object.get("chair").getAsString())
                .setSubject(object.get("subject").getAsString())
                .setPhoneNumber(object.get("phoneNumber").getAsString())
                .setHomeAddress(object.get("homeAddress").getAsString()).build();
        return teacher;
    }

    public Gson registerGsonBuilder(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Teacher.class, this);
        Gson gson = builder.create();
        return gson;
    }
    public String getJson(Map map){
        return registerGsonBuilder().toJson(map);
    }
    public String getJsonObjectStr(ArrayList<Faculty> faculties){
        Collection collection = faculties;
        return registerGsonBuilder().toJson(collection);
    }

    public List<Teacher> getListJson(String json){
        return registerGsonBuilder().fromJson(json,itemsListType);
    }
}
