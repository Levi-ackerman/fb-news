package com.project.levi.news.common;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.project.levi.news.data.models.Page;
import com.project.levi.news.data.models.Picture;

import java.lang.reflect.Type;

/**
 * Created by VyTK1 on 7/5/2016.
 */

public class PageDeserializer implements JsonDeserializer<Page> {
    private static final String TAG = "PageDeserializer";

    public PageDeserializer() {
    }

    @Override
    public Page deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Page page = gson.fromJson(json, Page.class);
        JsonObject obj = json.getAsJsonObject();
        JsonObject picObj = obj.getAsJsonObject("picture");
        JsonObject picData = picObj.getAsJsonObject("data");
        Picture picture = gson.fromJson(picData, Picture.class);
        page.setPicture(picture);
        return page;
    }
}
