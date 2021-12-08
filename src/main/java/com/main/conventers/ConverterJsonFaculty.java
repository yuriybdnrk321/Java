package com.main.conventers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.main.builder.FacultyBuilder;
import com.main.entity.Faculty;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConverterJsonFaculty implements JsonSerializer<Faculty>, JsonDeserializer<Faculty> {
    Type itemsListType = new TypeToken<List<Faculty>>() {}.getType();

    @Override
    public JsonElement serialize(Faculty src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("name",src.getNameFaculty());
        object.addProperty("number",src.getNumberFaculty());
        object.addProperty("countInFaculty",src.getCountStudentInFaculty());
        object.addProperty("numberPhone",src.getNumberPhone());
        return object;
    }


    @Override
    public Faculty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Faculty faculty = new FacultyBuilder().setNameFaculty(object.get("name").getAsString())
                .setNumberFaculty(object.get("number").getAsString())
                .setCountStudentInFaculty(object.get("countInFaculty").getAsLong())
                .setNumberPhone(object.get("numberPhone").getAsString()).build();
        return faculty;
    }

    public Gson registerGsonBuilder(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Faculty.class, this);
        Gson gson = builder.create();
        return gson;
    }
    public String getJson(Map map){
        return registerGsonBuilder().toJson(map);
    }

    public String getJson(Collection collection){
        return registerGsonBuilder().toJson(collection);
    }

    public String getJsonObjectStr(ArrayList<Faculty> faculties){
        Collection collection = faculties;
        return registerGsonBuilder().toJson(collection);
    }

    public List<Faculty> getListJson(String json){
        return registerGsonBuilder().fromJson(json,itemsListType);
    }
}
