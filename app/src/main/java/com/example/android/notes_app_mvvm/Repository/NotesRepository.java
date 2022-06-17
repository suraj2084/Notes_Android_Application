package com.example.android.notes_app_mvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.android.notes_app_mvvm.Database.notesDatabse;
import com.example.android.notes_app_mvvm.Dao.notesDao;
import com.example.android.notes_app_mvvm.model.notes;

import java.util.List;

public class NotesRepository {
    public notesDao NotesDao;
    public LiveData<List<notes>> GetallNotes;
    public LiveData<List<notes>> hightolow;
    public LiveData<List<notes>> lowtohigh;
    public NotesRepository(Application application){
        notesDatabse database=notesDatabse.getDatabseInstance(application);
        NotesDao=database.notesDao();
        GetallNotes =  NotesDao.GetallNotes();
        hightolow=NotesDao.hightolow();
        lowtohigh=NotesDao.lowtohigh();

    }
    public void insertnotes(notes note){
        NotesDao.insertNotes(note);
    }
   public void deletenotes(int id){
        NotesDao.deletenotes(id);
    }
    public void updatenotes(notes note){
        NotesDao.updatenotes(note);
    }


}
