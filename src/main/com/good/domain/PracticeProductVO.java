package com.good.domain;

public class PracticeProductVO {
    private String name;
    private double price;
    public PracticeProductVO(String name,double price){
        super();
        this.name = name;
        this.price = price;
    }

    public String getName(){return name;}
    public double getPrice(){return price;}
    public void setName(String name){this.name = name;}
    public void setPrice(double price){this.price = price;}

    @Override
    public String toString(){
        return "PracticeProductVO {"+"name='"+name+'\''+", price ="+price +'}';
    }
}
