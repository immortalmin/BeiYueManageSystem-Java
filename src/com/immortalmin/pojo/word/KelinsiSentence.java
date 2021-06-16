package com.immortalmin.pojo.word;

public class KelinsiSentence {
    private String sid;//例句id
    private String iid;//对应item的id

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    @Override
    public String toString() {
        return "KelinsiSentence{" +
                "sid='" + sid + '\'' +
                ", iid='" + iid + '\'' +
                '}';
    }
}
