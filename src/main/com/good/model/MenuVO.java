package com.good.model;

public class MenuVO {
    private int mid;
    private String code;
    private String codename;
    private int sort_num;
    private String comment;
    private String reg_id;
    private String reg_gt;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getReg_gt() {
        return reg_gt;
    }

    public void setReg_gt(String reg_gt) {
        this.reg_gt = reg_gt;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSort_num() {
        return sort_num;
    }

    public void setSort_num(int sort_num) {
        this.sort_num = sort_num;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //요거는 어디다가 쓰려고 만드는걸까..., 우선은 만들기만함
    @Override
    public String toString() {
        return "MenuVO [mid=" + mid + ", code=" + code + ", codename=" + codename + ", sort_num=" + sort_num + ", comment=" + comment + ", reg_id=" + reg_id + ", reg_gt=" + reg_gt + "]";
    }
}
