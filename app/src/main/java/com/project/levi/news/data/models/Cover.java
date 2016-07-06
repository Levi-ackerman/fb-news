package com.project.levi.news.data.models;

/**
 * Created by Levi on 7/6/2016.
 */

public class Cover {
    private String cover_id;
    private int offset_x;
    private int offset_y;
    private String source;

    public String getCover_id() {
        return cover_id;
    }

    public void setCover_id(String cover_id) {
        this.cover_id = cover_id;
    }

    public int getOffset_x() {
        return offset_x;
    }

    public void setOffset_x(int offset_x) {
        this.offset_x = offset_x;
    }

    public int getOffset_y() {
        return offset_y;
    }

    public void setOffset_y(int offset_y) {
        this.offset_y = offset_y;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
