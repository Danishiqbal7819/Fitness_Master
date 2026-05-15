package com.example.fitnessmaster;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BmiHistoryActivity extends AppCompatActivity {
    private ListView list;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private TextView totalEntries;
    private TextView latestEntry;
    private TextView historyEmpty;
    private ArrayList<vendor1> arrayList;
    private BmiHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.showData), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = findViewById(R.id.list);
        progressBar=findViewById(R.id.progress);
        toolbar=findViewById(R.id.toolbar);
        totalEntries = findViewById(R.id.totalEntries);
        latestEntry = findViewById(R.id.latestEntry);
        historyEmpty = findViewById(R.id.historyEmpty);

        progressBar.setVisibility(View.VISIBLE);
        arrayList = new ArrayList<>();
        adapter = new BmiHistoryAdapter(BmiHistoryActivity.this, arrayList, this::confirmDeleteEntry);
        list.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference().child("vendor1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String id = snapshot1.getKey();
                        String date = snapshot1.child("bmidate").getValue(String.class);
                        String bmi = snapshot1.child("bmivalue").getValue(String.class);
                        arrayList.add(0, new vendor1(id, date, bmi));
                    }
                }
                adapter.notifyDataSetChanged();
                updateSummary(arrayList);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BmiHistoryActivity.this, "failed to load", Toast.LENGTH_SHORT).show();
            }

        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });


    }

    private void updateSummary(ArrayList<vendor1> entries) {
        totalEntries.setText(String.valueOf(entries.size()));
        if (entries.isEmpty()) {
            latestEntry.setText("--");
            historyEmpty.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
            return;
        }

        historyEmpty.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        latestEntry.setText(entries.get(0).getbmi());
    }

    private void confirmDeleteEntry(vendor1 entry) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete BMI Entry");
        builder.setMessage("Delete the BMI entry saved for " + entry.getdate() + "?");
        builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> deleteEntry(entry));
        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void deleteEntry(vendor1 entry) {
        if (entry.getId() == null || entry.getId().trim().isEmpty()) {
            Toast.makeText(this, "Unable to delete this entry.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance()
                .getReference()
                .child("vendor1")
                .child(entry.getId())
                .removeValue()
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(BmiHistoryActivity.this, "BMI entry deleted.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BmiHistoryActivity.this, "Failed to delete entry.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    }

