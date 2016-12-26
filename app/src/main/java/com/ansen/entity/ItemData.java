package com.ansen.entity;

/**
 * Created by  ansen
 * Create Time 2016-12-20
 */
public class ItemData {
    private String content;//item内容
    private int height;//item高度

    public ItemData() {
    }

    public ItemData(String content, int height) {
        this.content = content;
        this.height = height;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
