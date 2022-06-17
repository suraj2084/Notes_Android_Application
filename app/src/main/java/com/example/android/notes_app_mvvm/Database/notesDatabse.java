package com.example.android.notes_app_mvvm.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.notes_app_mvvm.model.notes;
import com.example.android.notes_app_mvvm.Dao.notesDao;

@Database(entities={notes.class},version =1)
public abstract class notesDatabse extends RoomDatabase {
    public abstract notesDao notesDao();
    public static notesDatabse Instance;

    public static notesDatabse getDatabseInstance(Context context) {
        if (Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(), notesDatabse.class, "notes_Database").allowMainThreadQueries().build();
        }
            return Instance;

    }
}
