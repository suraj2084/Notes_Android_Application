package com.example.android.notes_app_mvvm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.jar.Attributes;

@Entity(tableName = "notes_Database")
public class notes {
    @PrimaryKey(autoGenerate = true)
        public int id;
    @ColumnInfo(name="notes_title")
        public String notesTitle;

    @ColumnInfo(name="notes_subtitle")
      public String notesSubtitle;

    @ColumnInfo(name="notes_date")
      public String notesDate;

    @ColumnInfo(name="notes")
      public String notes;

    @ColumnInfo(name="notes_Priority")
     public String notespriority;



}
