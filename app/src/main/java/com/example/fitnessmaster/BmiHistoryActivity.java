package com.example.fitnessmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
    private  Button data;
    private ListView list;
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;
    private Toolbar toolbar;
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

        progressBar.setVisibility(View.VISIBLE);
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<String>(BmiHistoryActivity.this, R.layout.items, arrayList);
        list.setAdapter(adapter);
        firebaseDatabase.getInstance().getReference().child("vendor1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    arrayList.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                        String name = snapshot1.child("name").getValue(String.class);
                        String age = snapshot1.child("age").getValue(String.class);
                        vendor1 i = new vendor1(name, age);
                        String t = "Date :" + i.getName() + "\nBMI :" + i.getAge();
                        arrayList.add(t);
                    }
                    adapter.notifyDataSetChanged();

                }
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

    }

