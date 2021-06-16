package com.immortalmin.pojo.word;

import java.util.ArrayList;

public class KelinsiWord extends Word{
    private String word_ch;
    private String star;//星级
//    private ArrayList<KelinsiItem> items = null;


    public String getWord_ch() {
        return word_ch;
    }

    public void setWord_ch(String word_ch) {
        this.word_ch = word_ch;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

//    public ArrayList<KelinsiItem> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<KelinsiItem> items) {
//        this.items = items;
//    }


    @Override
    public String toString() {
        return "KelinsiWord{" +
                "word_ch='" + word_ch + '\'' +
                ", star='" + star + '\'' +
                '}';
    }
}
