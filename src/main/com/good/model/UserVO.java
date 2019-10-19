package com.good.model;

public class UserVO {
    private String id;
    private String name;
    private String pwd;
    private String re_pwd;
    private String email;
    private String grade;
    private String reg_gt;


    public String getReg_gt() {
        return reg_gt;
    }

    public void setReg_gt(String reg_gt) {
        this.reg_gt = reg_gt;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRe_pwd() {
        return re_pwd;
    }

    public void setRe_pwd(String re_pwd) {
        this.re_pwd = re_pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
