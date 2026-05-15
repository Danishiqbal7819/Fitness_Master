package com.example.fitnessmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class BmiHistoryActivity extends AppCompatActivity {
    private ListView list;
//    private ProgressBar progressBar;
    private Toolbar toolbar;
    private TextView totalEntries;
    private TextView latestEntry;
    private TextView historyEmpty;
    private ArrayList<BmiEntry> arrayList;
    private BmiHistoryAdapter adapter;
    BmiDao db;
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
        db = AppDatabase.getInstance(BmiHistoryActivity.this).bmiDao();
        list = findViewById(R.id.list);
//        progressBar=findViewById(R.id.progress);
        toolbar=findViewById(R.id.toolbar);
        totalEntries = findViewById(R.id.totalEntries);
        latestEntry = findViewById(R.id.latestEntry);
        historyEmpty = findViewById(R.id.historyEmpty);

//        progressBar.setVisibility(View.VISIBLE);
        arrayList = new ArrayList<>();
        adapter = new BmiHistoryAdapter(BmiHistoryActivity.this, arrayList,db);
        list.setAdapter(adapter);
//        FirebaseDatabase.getInstance().getReference().child("vendor1").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrayList.clear();
//                if (snapshot.exists()) {
//                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                        String id = snapshot1.getKey();
//                        String date = snapshot1.child("bmidate").getValue(String.class);
//                        String bmi = snapshot1.child("bmivalue").getValue(String.class);
//                        arrayList.add(0, new vendor1(id, date, bmi));
//                    }
//                }
//                adapter.notifyDataSetChanged();
//                updateSummary(arrayList);
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(BmiHistoryActivity.this, "failed to load", Toast.LENGTH_SHORT).show();
//            }
//
//        });
try {
    Executors.newSingleThreadExecutor().execute(() -> {

        BmiDao db = AppDatabase.getInstance(BmiHistoryActivity.this).bmiDao();

        List<BmiEntry> data = db.getAllEntries();

        runOnUiThread(() -> {
            arrayList.clear();
            arrayList.addAll(data);
            adapter.notifyDataSetChanged();
            updateSummary(arrayList);
        });
    });
}
catch (Exception e){

}

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });


    }

    private void updateSummary(ArrayList<BmiEntry> entries) {
        totalEntries.setText(String.valueOf(entries.size()));
        if (entries.isEmpty()) {
            latestEntry.setText("--");
            historyEmpty.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
            return;
        }

        historyEmpty.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        latestEntry.setText(entries.get(0).getBmivalue());
    }
    }

