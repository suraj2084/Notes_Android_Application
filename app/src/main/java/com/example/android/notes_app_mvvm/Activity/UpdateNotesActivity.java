package com.example.android.notes_app_mvvm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.notes_app_mvvm.R;
import com.example.android.notes_app_mvvm.ViewModel.NotesViewmodel;
import com.example.android.notes_app_mvvm.databinding.ActivityUpdateNotesBinding;
import com.example.android.notes_app_mvvm.model.notes;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    ActivityUpdateNotesBinding binding;
    String Ititle, Isubtitle, INotes, IPriority;
    String Priority = "1";
    int iid;
    NotesViewmodel noteViewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewmodel= new ViewModelProvider(this ).get(NotesViewmodel.class);
        iid = getIntent().getIntExtra("id", 0);
        Ititle = getIntent().getStringExtra("title");
        Isubtitle = getIntent().getStringExtra("subtitle");
        INotes = getIntent().getStringExtra("Data");
        IPriority = getIntent().getStringExtra("Priority");
        binding.Uptitle.setText(Ititle);
        binding.UpSubtitle.setText(Isubtitle);
        binding.UpData.setText(INotes);
        if (IPriority.equals("1")) {
            binding.greenPriorty.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriorty.setImageResource(0);
            binding.redPriorty.setImageResource(0);
        } else if (IPriority.equals("2")) {
            binding.greenPriorty.setImageResource(0);
            binding.yellowPriorty.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriorty.setImageResource(0);
        } else if (IPriority.equals("3")) {
            binding.greenPriorty.setImageResource(0);
            binding.yellowPriorty.setImageResource(0);
            binding.redPriorty.setImageResource(R.drawable.ic_baseline_done_24);

        }
        binding.greenPriorty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriorty.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellowPriorty.setImageResource(0);
                binding.redPriorty.setImageResource(0);
                Priority = "1";
            }
        });
        binding.yellowPriorty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriorty.setImageResource(0);
                binding.yellowPriorty.setImageResource(R.drawable.ic_baseline_done_24);
                binding.redPriorty.setImageResource(0);
                Priority = "2";
            }
        });
        binding.redPriorty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriorty.setImageResource(0);
                binding.yellowPriorty.setImageResource(0);
                binding.redPriorty.setImageResource(R.drawable.ic_baseline_done_24);
                Priority = "3";
            }
        });
        binding.UpButton.setOnClickListener(view -> {

            String uptitle = binding.Uptitle.getText().toString();
            String upsubtitle = binding.UpSubtitle.getText().toString();
            String updata = binding.UpData.getText().toString();
            cretaenotes(uptitle, upsubtitle, updata);
        });


    }

    private void cretaenotes(String title, String subtitle, String data) {
        Toast.makeText(this, "Notes update added ", Toast.LENGTH_SHORT).show();
        notes updateNotes=new notes();
        Date date = new Date();
        CharSequence sequence= DateFormat.format("MMMM d ,yyyy",date.getTime());
        updateNotes.notesTitle=title;
        updateNotes.notesSubtitle=subtitle;
        updateNotes.notes=data;
        updateNotes.notespriority=Priority;
        updateNotes.notesDate=sequence.toString();
        updateNotes.id=iid;
        noteViewmodel.updatenote(updateNotes);
        Toast.makeText(this, "Notes update added ", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete){
            BottomSheetDialog sheetDialog=new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);
            View view= LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_button_sheet,
                    (LinearLayout)findViewById(R.id.buttonSheet));
            sheetDialog.setContentView(view);
            TextView yes,no;
            yes=view.findViewById(R.id.yes);
            no=view.findViewById(R.id.no);
            yes.setOnClickListener(v ->{
                noteViewmodel.deletenote(iid);
                finish();
            });
            no.setOnClickListener(v ->{
                sheetDialog.dismiss();
            });
            sheetDialog.show();
        }
        return true;
    }
}