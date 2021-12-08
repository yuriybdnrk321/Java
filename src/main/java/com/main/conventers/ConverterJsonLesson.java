package com.main.conventers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.main.builder.FacultyBuilder;
import com.main.builder.GroupBuilder;
import com.main.builder.LessonBuilder;
import com.main.entity.Faculty;
import com.main.entity.Group;
import com.main.entity.Lesson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConverterJsonLesson implements JsonSerializer<Lesson>, JsonDeserializer<Lesson> {
    Type itemsListType = new TypeToken<List<Lesson>>() {}.getType();

    @Override
    public JsonElement serialize(Lesson src, Type typeOfSrc, JsonSerializationContext context) {


        JsonObject objectLesson = new JsonObject();
        objectLesson.addProperty("nameLesson",src.getNameLesson());
        objectLesson.addProperty("timeStart",src.getTimeStart());
        objectLesson.addProperty("numberClass",src.getNumberClass());
        objectLesson.addProperty("typeLesson",src.getTypeLesson());


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

/*        JsonObject objectTeacher = new JsonObject();
        objectTeacher.addProperty("number",src.getGroup().getNumber());
        objectTeacher.addProperty("course",src.getGroup().getCourse());
        objectTeacher.addProperty("direction",src.getGroup().getDirection());
        JsonElement elementTeacher = objectTeacher;*/

        objectLesson.add("group",elementGroup);
       // objectLesson.add("teacher",elementTeacher);

        return objectLesson;
    }


    @Override
    public Lesson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

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


        Lesson lesson = new LessonBuilder().setNameLesson(object.get("nameLesson").getAsString())
                .setTypeLesson(object.get("typeLesson").getAsString())
                .setNumberClass(object.get("numberClass").getAsString())
                .setTimeStart(object.get("timeStart").getAsString())
                .setGroup(group).build();
        return lesson;
    }

    public Gson registerGsonBuilder(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Lesson.class, this);
        Gson gson = builder.create();
        return gson;
    }
    public String getJson(Map map){
        return registerGsonBuilder().toJson(map);
    }
    public String getJsonObjectStr(ArrayList<Lesson> faculties){
        Collection collection = faculties;
        return registerGsonBuilder().toJson(collection);
    }

    public List<Lesson> getListJson(String json){
        return registerGsonBuilder().fromJson(json,itemsListType);
    }
}
