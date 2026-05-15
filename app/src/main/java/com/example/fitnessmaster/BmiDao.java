package com.example.fitnessmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BmiDao {
    @Insert
    long insert(BmiEntry bmiEntry);

    @Query("SELECT * FROM bmi_entries ORDER BY id DESC")
    List<BmiEntry> getAllEntries();
    @Query("DELETE FROM bmi_entries WHERE id = :entryId")
    void deleteEntry(int entryId);
}
