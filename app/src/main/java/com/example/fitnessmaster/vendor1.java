package com.example.fitnessmaster;

public class vendor1 {
   public String id;
   public String date;
    public String bmi;

    public String url;

    public float HEIGHT,WEIGHT;
    public vendor1(){

    }

    public String getdate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getbmi() {
        return bmi;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setbmi(String bmi) {
        this.bmi = bmi;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String geturl() {
        return url;
    }
    public vendor1(String date, String bmi) {
        this.date = date;
        this.bmi = bmi;
    }

    public vendor1(String id, String date, String bmi) {
        this.id = id;
        this.date = date;
        this.bmi = bmi;
    }

    public vendor1(String url){
        this.url=url;
    }

    public  float BMI( float H,float W){
        this.HEIGHT=H;
        this.WEIGHT=W;
        return (W/H*H);
    }
}

