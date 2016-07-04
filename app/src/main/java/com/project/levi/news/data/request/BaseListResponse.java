package com.project.levi.news.data.request;

import java.util.List;

/**
 * Created by Levi on 7/4/2016.
 */

public class BaseListResponse<T> {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
