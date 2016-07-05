package com.project.levi.news.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.levi.news.data.models.Page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VyTK1 on 7/5/2016.
 */

public class ParseJsonUtils {

    public static List<Page> parseJsonObjectToListPage(JSONObject jsonObject) {
        List<Page> pageList = new ArrayList<>();
        try {
            if (jsonObject.has("data")) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if(jsonArray != null && jsonArray.length() > 0){
                    JSONObject obj;

                    GsonBuilder builder = new GsonBuilder();
                    builder.registerTypeAdapter(Page.class, new PageDeserializer());
                    Gson gson = builder.create();
                    Page page;
                    for(int i = 0; i < jsonArray.length(); i++){
                        obj = jsonArray.getJSONObject(i);
                        page = gson.fromJson(obj.toString(), Page.class);
                        System.out.println(page.getPicture().getUrl());
                        pageList.add(page);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pageList;
    }
}
