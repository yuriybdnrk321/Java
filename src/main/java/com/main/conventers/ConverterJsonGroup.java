package com.main.conventers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.main.builder.FacultyBuilder;
import com.main.builder.GroupBuilder;
import com.main.entity.Faculty;
import com.main.entity.Group;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConverterJsonGroup implements JsonSerializer<Group>, JsonDeserializer<Group> {
    Type itemsListType = new TypeToken<List<Group>>() {}.getType();

    @Override
    public JsonElement serialize(Group src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject object = new JsonObject();
        object.addProperty("number",src.getNumber());
        object.addProperty("course",src.getCourse());
        object.addProperty("direction",src.getDirection());

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
    public Group deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        JsonObject facultyObject = object.getAsJsonObject("faculty");
        Faculty faculty = new FacultyBuilder()
                .setNameFaculty(facultyObject.get("name").getAsString())
                .setNumberFaculty(facultyObject.get("number").getAsString())
                .setCountStudentInFaculty(facultyObject.get("countInFaculty").getAsLong())
                .setNumberPhone(facultyObject.get("numberPhone").getAsString()).build();

        Group group = new GroupBuilder().setCourse(object.get("course").getAsString())
                .setDirection(object.get("direction").getAsString())
                .setNumber(object.get("number").getAsString())
                .setFaculty(faculty).build();
        return group;
    }

    public Gson registerGsonBuilder(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Group.class, this);
        Gson gson = builder.create();
        return gson;
    }
    public String getJson(Map map){
        return registerGsonBuilder().toJson(map);
    }
    public String getJson(Collection collection){
        return registerGsonBuilder().toJson(collection);
    }
    public String getJsonObjectStr(ArrayList<Group> groups){
        Collection collection = groups;
        return registerGsonBuilder().toJson(collection);
    }

    public List<Group> getListJson(String json){
        return registerGsonBuilder().fromJson(json,itemsListType);
    }
}
