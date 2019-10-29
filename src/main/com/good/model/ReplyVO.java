package com.good.model;

public class ReplyVO {
    private int rid;
    private int bid;
    private String content;
    private String reg_id;
    private String reg_gt;
    private String edit_gt;
    private String id;
    private int readonlyorwrite; // 1이면 해당 아이디인 사람이 쓴것, 0이면 해당 아이디인 사람이 쓴것이 아님
    public int getReadonlyorwrite() {
        return readonlyorwrite;
    }

    public void setReadonlyorwrite(int readonlyorwrite) {
        this.readonlyorwrite = readonlyorwrite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getEdit_gt() {
        return edit_gt;
    }

    public void setEdit_gt(String edit_gt) {
        this.edit_gt = edit_gt;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

}
