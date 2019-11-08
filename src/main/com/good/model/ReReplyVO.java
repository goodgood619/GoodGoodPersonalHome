package com.good.model;

public class ReReplyVO {
    private int rrid;
    private int rid;
    private String r_content;
    private String r_reg_id;
    private String r_reg_gt;
    private String r_edit_gt;
    private String id;
    private int r_readonlyorwrite;

    public int getR_readonlyorwrite() {
        return r_readonlyorwrite;
    }

    public void setR_readonlyorwrite(int r_readonlyorwrite) {
        this.r_readonlyorwrite = r_readonlyorwrite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getR_edit_gt() {
        return r_edit_gt;
    }

    public void setR_edit_gt(String r_edit_gt) {
        this.r_edit_gt = r_edit_gt;
    }

    public String getR_reg_gt() {
        return r_reg_gt;
    }

    public void setR_reg_gt(String r_reg_gt) {
        this.r_reg_gt = r_reg_gt;
    }

    public String getR_reg_id() {
        return r_reg_id;
    }

    public void setR_reg_id(String r_reg_id) {
        this.r_reg_id = r_reg_id;
    }

    public String getR_content() {
        return r_content;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRrid() {
        return rrid;
    }

    public void setRrid(int rrid) {
        this.rrid = rrid;
    }
}
