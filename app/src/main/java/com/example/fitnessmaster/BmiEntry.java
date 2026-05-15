package com.example.fitnessmaster;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bmi_entries")
public class BmiEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bmidate;
    private String bmivalue;

    public BmiEntry(String bmidate, String bmivalue) {
        this.bmidate = bmidate;
        this.bmivalue = bmivalue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBmidate() {
        return bmidate;
    }

    public void setBmidate(String bmidate) {
        this.bmidate = bmidate;
    }

    public String getBmivalue() {
        return bmivalue;
    }

    public void setBmivalue(String bmivalue) {
        this.bmivalue = bmivalue;
    }
}
