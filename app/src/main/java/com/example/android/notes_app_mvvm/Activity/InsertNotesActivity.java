package com.example.android.notes_app_mvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.android.notes_app_mvvm.R;
import com.example.android.notes_app_mvvm.ViewModel.NotesViewmodel;
import com.example.android.notes_app_mvvm.databinding.ActivityInsertBinding;
import com.example.android.notes_app_mvvm.model.notes;

import java.util.Date;


public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertBinding binding;
    String title, subtitle, data;
    String Priority="1";

    NotesViewmodel noteViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_insert);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewmodel= new ViewModelProvider(this ).get(NotesViewmodel.class);

    binding.doneNotesButton.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        title = binding.insertTitle.getText().toString();
        subtitle = binding.SubTtile.getText().toString();
        data = binding.notesData.getText().toString();
        cretaenotes(title,subtitle,data);
    }
    });
    binding.greenPriorty.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            binding.greenPriorty.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriorty.setImageResource(0);
            binding.redPriorty.setImageResource(0);
            Priority="1";
        }
    });
        binding.yellowPriorty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriorty.setImageResource(0);
                binding.yellowPriorty.setImageResource(R.drawable.ic_baseline_done_24);
                binding.redPriorty.setImageResource(0);
                Priority="2";
            }
        });
        binding.redPriorty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriorty.setImageResource(0);
                binding.yellowPriorty.setImageResource(0);
                binding.redPriorty.setImageResource(R.drawable.ic_baseline_done_24);
                Priority="3";
            }
        });
}
    private void cretaenotes(String title, String subtitle, String data) {
        notes note1=new notes();
        Date date = new Date();
        CharSequence sequence= DateFormat.format("MMMM d ,yyyy",date.getTime());
        note1.notesTitle=title;
        note1.notesSubtitle=subtitle;
        note1.notes=data;
        note1.notespriority=Priority;
        note1.notesDate=sequence.toString();
        noteViewmodel.insertnote(note1);
        Toast.makeText(this, "Notes  added ", Toast.LENGTH_SHORT).show();
        finish();
    }

}