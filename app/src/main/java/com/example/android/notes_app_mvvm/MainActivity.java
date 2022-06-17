package com.example.android.notes_app_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.android.notes_app_mvvm.Activity.InsertNotesActivity;
import com.example.android.notes_app_mvvm.Activity.splashScreen;
import com.example.android.notes_app_mvvm.Adpter.NotesAdpter;
import com.example.android.notes_app_mvvm.ViewModel.NotesViewmodel;
import com.example.android.notes_app_mvvm.model.notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add;
    TextView nofilter, lowtohigh, hightolow;
    NotesViewmodel noteViewmodel;
    RecyclerView notesRecycler;
    List<notes> filternameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        nofilter = findViewById(R.id.nofilter);
        lowtohigh = findViewById(R.id.lowtohigh);
        hightolow = findViewById(R.id.hightolow);
        notesRecycler = findViewById(R.id.RVIEW);

        nofilter.setBackgroundResource(R.drawable.filter_select_shap);
        nofilter.setOnClickListener(v -> {
            loaddata(0);
            nofilter.setBackgroundResource(R.drawable.filter_select_shap);
            lowtohigh.setBackgroundResource(R.drawable.filter_shape);
            hightolow.setBackgroundResource(R.drawable.filter_shape);
        });
        lowtohigh.setOnClickListener(v -> {
            loaddata(1);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_select_shap);
            hightolow.setBackgroundResource(R.drawable.filter_shape);
        });
        hightolow.setOnClickListener(v -> {
            loaddata(2);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_shape);
            hightolow.setBackgroundResource(R.drawable.filter_select_shap);
        });
        noteViewmodel = new ViewModelProvider(this).get(NotesViewmodel.class);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));

            }
        });
        noteViewmodel.getallnotes.observe(this,notes ->{
            notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            notesRecycler.setAdapter(new NotesAdpter(MainActivity.this, notes));
            filternameList =notes;
        });
    }

    private void loaddata(int i) {
        if (i == 0) {
            noteViewmodel.getallnotes.observe(this, notes -> {
                notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                notesRecycler.setAdapter(new NotesAdpter(MainActivity.this, notes));
                filternameList =notes;
            });

        } else if (i == 1) {
            noteViewmodel.hightolow.observe(this, notes -> {
                notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                notesRecycler.setAdapter(new NotesAdpter(MainActivity.this, notes));
                 filternameList =notes;
            });

        } else if (i == 2) {

            noteViewmodel.lowtohigh.observe(this, notes -> {
                notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                notesRecycler.setAdapter(new NotesAdpter(MainActivity.this, notes));
                filternameList =notes;
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.app_bar_search);
         SearchView searchview= (SearchView) menuItem.getActionView();
         searchview.setQueryHint("Search Notes Here...");
         searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String s) {
                 notesFilter(s);
                 return false;
             }



             @Override
             public boolean onQueryTextChange(String s) {
                 return false;
             }
         });

        return super.onCreateOptionsMenu(menu);
    }
    private void notesFilter(String s) {
        ArrayList<notes> filternames =new ArrayList<>();
        for(notes notes:this.filternameList){
            if(notes.notesTitle.contains(s)||notes.notesSubtitle.contains(s)){
                filternames.add(notes);
            }
        }

    }
}
