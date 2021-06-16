package com.immortalmin.pojo.word;

import java.util.ArrayList;

public class KelinsiWord extends Word{
    private String star;//星级
    private ArrayList<KelinsiItem> items = null;

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public ArrayList<KelinsiItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<KelinsiItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "KelinsiWord{" +
                "star='" + star + '\'' +
                ", items=" + items +
                '}';
    }
}
