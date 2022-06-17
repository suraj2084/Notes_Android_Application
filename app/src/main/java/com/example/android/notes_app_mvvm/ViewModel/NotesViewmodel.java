package com.example.android.notes_app_mvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.notes_app_mvvm.Repository.NotesRepository;
import com.example.android.notes_app_mvvm.model.notes;

import java.io.Closeable;
import java.util.List;

public class NotesViewmodel extends AndroidViewModel {
   public  NotesRepository repository;
    public LiveData<List<notes>> getallnotes;
    public LiveData<List<notes>> hightolow;
    public LiveData<List<notes>> lowtohigh;

    public NotesViewmodel(@NonNull Application application) {
        super(application);
        repository=new NotesRepository(application);
        getallnotes=repository.GetallNotes;
        hightolow=repository.hightolow;
        lowtohigh=repository.lowtohigh;

    }

    public void insertnote(notes n){
        repository.insertnotes(n);
    }
    public void deletenote(int id){
        repository.deletenotes(id);
    }
    public void updatenote(notes note){
        repository.updatenotes(note);
    }
//
}

