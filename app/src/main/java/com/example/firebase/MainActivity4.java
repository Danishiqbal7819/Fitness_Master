package com.example.firebase;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import com.example.firebase.ui.Adapter;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ImageView titleImage;
private Button backbutton;
private Toolbar toolbar;
    TextView Titletext;
    ArrayList<Integer> list=new ArrayList<>();

ArrayList<String> Strings=new ArrayList<>();
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
//        backbutton=findViewById(R.id.backbuttonn);
        RecyclerView recyclerView = findViewById(R.id.recycler);

        ArrayList<String> filelist =  (ArrayList<String>)getIntent().getSerializableExtra("name");
        ArrayList<Integer> filelist1 =  (ArrayList<Integer>)getIntent().getSerializableExtra("name1");


        String titleText= (String) getIntent().getSerializableExtra("title");

        Titletext.setText(titleText.toString());
        titleImage.setImageResource(filelist1.get(1));
//       try {
//           Picasso.get()
//                   .load(imageUrls.get(0))
//                   .into(titleImage);
//       }
//       catch (Exception e){
//           Toast.makeText(getApplication(),""+e,Toast.LENGTH_SHORT).show();
//       }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new Adapter((Context) getApplication(),filelist, filelist1));

//backbutton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent intent=new Intent(MainActivity4.this, FitnessActivity.class);
//        finish();
//        startActivity(intent);
//    }
//});

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });



    }


}