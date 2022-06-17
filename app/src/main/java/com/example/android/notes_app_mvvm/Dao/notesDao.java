package com.example.android.notes_app_mvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.notes_app_mvvm.model.notes;

import java.util.List;

@androidx.room.Dao
public interface notesDao {
    @Query("SELECT * FROM NOTES_DATABASE")
    LiveData<List<notes>>  GetallNotes();
    @Query("SELECT * FROM NOTES_DATABASE ORDER BY notes_Priority DESC")
    LiveData<List<notes>>  hightolow();
    @Query("SELECT * FROM NOTES_DATABASE ORDER BY notes_Priority ASC")
    LiveData<List<notes>>  lowtohigh();
    @Insert
    void insertNotes(notes... notes);
    @Query("DELETE FROM NOTES_DATABASE WHERE ID=:id")
    Void deletenotes(int id);
    @Update
    void updatenotes(notes notes);

}
