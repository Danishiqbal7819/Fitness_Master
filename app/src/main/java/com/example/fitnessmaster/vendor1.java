package com.example.fitnessmaster;

public class vendor1 {
   public String name;
    public String age;

    public String url;

    public float HEIGHT,WEIGHT;
    public vendor1(){

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String geturl() {
        return url;
    }
    public vendor1(String name, String age) {
        this.name = name;
        this.age = age;
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

