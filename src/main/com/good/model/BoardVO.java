package com.good.model;

public class BoardVO {
    private int bid;
    private String cate_cd;
    private String title;
    private String content;
    private String tag;
    private int view_cnt;
    private String reg_id;
    private String reg_gt;
    private String edit_gt;
    private String board_img;
    private String boardthumb_img;
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBoardthumb_img() {
        return boardthumb_img;
    }
    public void setBoardthumb_img(String boardthumb_img) {
        this.boardthumb_img = boardthumb_img;
    }

    public String getBoard_img() {
        return board_img;
    }
    public void setBoard_img(String board_img) {
        this.board_img = board_img;
    }

    public int getBid() {return bid; }
    public void setBid(int bid) { this.bid = bid; }

    public String getCate_cd() {return cate_cd; }
    public void setCate_cd(String cate_cd) { this.cate_cd = cate_cd; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public int getView_cnt() { return view_cnt; }
    public void setView_cnt(int view_cnt) { this.view_cnt = view_cnt; }

    public String getReg_id() { return reg_id; }
    public void setReg_id(String reg_id) { this.reg_id = reg_id; }

    public String getReg_gt(){return reg_gt;}
    public void setReg_gt(String reg_gt){this.reg_gt = reg_gt;}

    public String getEdit_gt(){return edit_gt;}
    public void setEdit_gt(String edit_gt){this.edit_gt = edit_gt;}

    public String eraseStringContent(String s){

        s = s.replaceAll("(\r\n|\r|\n|\n\r)", " ");
        return s;
    }
}
