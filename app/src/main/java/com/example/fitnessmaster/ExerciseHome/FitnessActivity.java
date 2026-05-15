package com.example.fitnessmaster.ExerciseHome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitnessmaster.ExerciseListActivity;
import com.example.fitnessmaster.R;

import java.util.ArrayList;
import java.util.List;

public class FitnessActivity extends AppCompatActivity {
    private ListView workoutListView;
    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView subtitleTextView;
    private ImageView heroImageView;

    private ArrayList<String> workoutSetOne;
    private ArrayList<String> workoutSetTwo;
    private ArrayList<String> workoutSetThree;
    private ArrayList<Integer> workoutImagesOne;
    private ArrayList<Integer> workoutImagesTwo;
    private ArrayList<Integer> workoutImagesThree;
    private String workoutTitle;

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

        bindViews();
        readIntentData();
        populateHeader();
        populateWorkoutPlan();
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void bindViews() {
        workoutListView = findViewById(R.id.list1);
        heroImageView = findViewById(R.id.image1);
        toolbar = findViewById(R.id.toolbar);
        titleTextView = findViewById(R.id.title_text);
        subtitleTextView = findViewById(R.id.title_subtitle);
    }

    @SuppressWarnings("unchecked")
    private void readIntentData() {
        workoutSetOne = (ArrayList<String>) getIntent().getSerializableExtra("name1");
        workoutSetTwo = (ArrayList<String>) getIntent().getSerializableExtra("name2");
        workoutSetThree = (ArrayList<String>) getIntent().getSerializableExtra("name3");
        workoutImagesOne = (ArrayList<Integer>) getIntent().getSerializableExtra("pic1");
        workoutImagesTwo = (ArrayList<Integer>) getIntent().getSerializableExtra("pic2");
        workoutImagesThree = (ArrayList<Integer>) getIntent().getSerializableExtra("pic3");
        workoutTitle = (String) getIntent().getSerializableExtra("title");
    }

    private void populateHeader() {
        titleTextView.setText(workoutTitle);
        subtitleTextView.setText(getString(R.string.workout_plan_subtitle));
        if (workoutImagesOne != null && !workoutImagesOne.isEmpty()) {
            heroImageView.setImageResource(workoutImagesOne.get(0));
        }
    }

    private void populateWorkoutPlan() {
        List<WorkoutDayAdapter.WorkoutDayItem> planItems = new ArrayList<>();
        planItems.add(new WorkoutDayAdapter.WorkoutDayItem("START", getString(R.string.workout_week_1_title), getString(R.string.workout_week_1_subtitle), true));
        addWorkoutDays(planItems, 1, 7);
        planItems.add(new WorkoutDayAdapter.WorkoutDayItem("LEVEL UP", getString(R.string.workout_week_2_title), getString(R.string.workout_week_2_subtitle), true));
        addWorkoutDays(planItems, 8, 14);
        planItems.add(new WorkoutDayAdapter.WorkoutDayItem("FINISH", getString(R.string.workout_week_3_title), getString(R.string.workout_week_3_subtitle), true));
        addWorkoutDays(planItems, 15, 21);

        workoutListView.setAdapter(new WorkoutDayAdapter(this, planItems));
        workoutListView.setOnItemClickListener((parent, view, position, id) -> {
            WorkoutDayAdapter.WorkoutDayItem item = (WorkoutDayAdapter.WorkoutDayItem) parent.getItemAtPosition(position);
            if (item.isHeader()) {
                return;
            }

            int dayNumber = extractDayNumber(item.getTitle());
            int phaseIndex = (dayNumber - 1) % 3;
            openWorkoutDay(phaseIndex);
        });
    }

    private void addWorkoutDays(List<WorkoutDayAdapter.WorkoutDayItem> planItems, int startDay, int endDay) {
        for (int day = startDay; day <= endDay; day++) {
            int phaseIndex = (day - 1) % 3;
            String subtitle;
            if (phaseIndex == 0) {
                subtitle = getString(R.string.workout_day_subtitle_1);
            } else if (phaseIndex == 1) {
                subtitle = getString(R.string.workout_day_subtitle_2);
            } else {
                subtitle = getString(R.string.workout_day_subtitle_3);
            }
            planItems.add(new WorkoutDayAdapter.WorkoutDayItem("DAY " + day, "Day " + day, subtitle, false));
        }
    }

    private int extractDayNumber(String title) {
        return Integer.parseInt(title.replace("Day ", "").trim());
    }

    private void openWorkoutDay(int phaseIndex) {
        Intent intent = new Intent(FitnessActivity.this, ExerciseListActivity.class);
        if (phaseIndex == 0) {
            intent.putExtra("name", workoutSetOne);
            intent.putExtra("name1", workoutImagesOne);
        } else if (phaseIndex == 1) {
            intent.putExtra("name", workoutSetTwo);
            intent.putExtra("name1", workoutImagesTwo);
        } else {
            intent.putExtra("name", workoutSetThree);
            intent.putExtra("name1", workoutImagesThree);
        }
        intent.putExtra("title", workoutTitle);
        startActivity(intent);
    }
}
