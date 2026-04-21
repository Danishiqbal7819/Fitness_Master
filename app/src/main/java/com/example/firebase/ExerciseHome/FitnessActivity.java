package com.example.firebase.ExerciseHome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase.MainActivity4;
import com.example.firebase.R;

import java.util.ArrayList;

public class FitnessActivity extends AppCompatActivity {
    private ListView list;
    ArrayList<String> Strings1=new ArrayList<>();
    ArrayList<String> Strings2=new ArrayList<>();
    ArrayList<String> Strings3=new ArrayList<>();
    ArrayList<Integer> list1=new ArrayList<>();
    ArrayList<Integer> list2=new ArrayList<>();
    ArrayList<Integer> list3=new ArrayList<>();
    Toolbar toolbar;
    TextView titletext1;
    ImageView image1;
    TextView textt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fitness);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainAct), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list =findViewById(R.id.list1);
        image1=findViewById(R.id.image1);
        toolbar=findViewById(R.id.toolbar);
        titletext1=findViewById(R.id.title_text);

        ArrayList<String> Strings1 = (ArrayList<String>) getIntent().getSerializableExtra("name1");
        ArrayList<String> Strings2 = (ArrayList<String>) getIntent().getSerializableExtra("name2");
        ArrayList<String> Strings3 = (ArrayList<String>) getIntent().getSerializableExtra("name3");

        ArrayList<Integer> list1 = (ArrayList<Integer>) getIntent().getSerializableExtra("pic1");
        ArrayList<Integer> list2 = (ArrayList<Integer>) getIntent().getSerializableExtra("pic2");
        ArrayList<Integer> list3 = (ArrayList<Integer>) getIntent().getSerializableExtra("pic3");


        String titleText= (String) getIntent().getSerializableExtra("title");


        image1.setImageResource(list1.get(1));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Week_1  <> <>");
        arrayList.add("Day 1");
        arrayList.add("Day 2");
        arrayList.add("Day 3");
        arrayList.add("Day 4");
        arrayList.add("Day 5");
        arrayList.add("Day 6");
        arrayList.add("Day 7");
        arrayList.add("Week_2  <> <>");
        arrayList.add("Day 8");
        arrayList.add("Day 9");
        arrayList.add("Day 10");
        arrayList.add("Day 11");
        arrayList.add("Day 12");
        arrayList.add("Day 13");
        arrayList.add("Day 14");
        arrayList.add("Week_3  <> <>");
        arrayList.add("Day 15");
        arrayList.add("Day 16");
        arrayList.add("Day 17");
        arrayList.add("Day 18");
        arrayList.add("Day 19");
        arrayList.add("Day 20");
        arrayList.add("Day 21");
        try {
//          ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.items,arrayList);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(FitnessActivity.this, R.layout.items, arrayList);
            list.setAdapter(adapter1);
        } catch (Exception e) {
            Toast.makeText(FitnessActivity.this, " " + e, Toast.LENGTH_SHORT).show();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (position == 1) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                } else if (position == 2) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 3) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings3);
                        intent.putExtra("name1", list3);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 4) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 5) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 6) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings3);
                        intent.putExtra("name1", list3);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 7) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 9) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 10) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings3);
                        intent.putExtra("name1", list3);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 11) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 12) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                } else if (position == 13) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings3);
                        intent.putExtra("name1", list3);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 14) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 15) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 17) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings3);
                        intent.putExtra("name1", list3);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 18) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 19) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 20) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings3);
                        intent.putExtra("name1", list3);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 21) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings1);
                        intent.putExtra("name1", list1);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position == 22) {
                    try {
                        Intent intent = new Intent(FitnessActivity.this, MainActivity4.class);
                        intent.putExtra("name", Strings2);
                        intent.putExtra("name1", list2);
                        intent.putExtra("title",titleText);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(FitnessActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }

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