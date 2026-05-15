package com.example.fitnessmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessmaster.ui.Adapter;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity {

    private ImageView titleImage;
    private Toolbar toolbar;
    TextView Titletext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleImage=findViewById(R.id.titleImage);
        Titletext=findViewById(R.id.title_exercise);
        toolbar=findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recycler);

        ArrayList<String> filelist =  (ArrayList<String>)getIntent().getSerializableExtra("name");
        ArrayList<Integer> filelist1 =  (ArrayList<Integer>)getIntent().getSerializableExtra("name1");


        String titleText= (String) getIntent().getSerializableExtra("title");

        Titletext.setText(titleText);
        if (filelist1 != null && !filelist1.isEmpty()) {
            titleImage.setImageResource(filelist1.get(0));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new Adapter(this, filelist, filelist1));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });



    }


}
