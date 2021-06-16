package com.immortalmin.pojo.word;

public class OtherWord extends Word{
    private String word_ch;
    private int source;

    public String getWord_ch() {
        return word_ch;
    }

    public void setWord_ch(String word_ch) {
        this.word_ch = word_ch;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "OtherWord{" +
                "wid='" + getWid() + '\'' +
                "word_en='" + getWord_en() + '\'' +
                "word_ch='" + word_ch + '\'' +
                ", source=" + source +
                '}';
    }
}
