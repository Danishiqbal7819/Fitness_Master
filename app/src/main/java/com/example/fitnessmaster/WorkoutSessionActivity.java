package com.example.fitnessmaster;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class WorkoutSessionActivity extends AppCompatActivity {

    private static final long TOTAL_DURATION_MS = 60_000L;

    private TextView titleText;
    private TextView statusText;
    private TextView statusSubtext;
    private TextView timer;
    private GifImageView gifImageView;
    private GifImageView celebration;
    private ImageView titleImage;
    private Button stopButton;
    private Button startButton;
    private CountDownTimer countDownTimer;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindViews();
        populateExerciseContent();
        setupToolbar();
        showGuidance();
        setupActions();
        resetWorkoutUi();
    }

    private void bindViews() {
        titleText = findViewById(R.id.title_text);
        gifImageView = findViewById(R.id.title_gif);
        timer = findViewById(R.id.timer);
        startButton = findViewById(R.id.StartButton);
        stopButton = findViewById(R.id.StopButton);
        statusText = findViewById(R.id.statustask);
        statusSubtext = findViewById(R.id.statusSubtext);
        celebration = findViewById(R.id.celebration);
        titleImage = findViewById(R.id.title_image);
    }

    @SuppressWarnings("unchecked")
    private void populateExerciseContent() {
        ArrayList<String> names = (ArrayList<String>) getIntent().getSerializableExtra("name");
        ArrayList<Integer> images = (ArrayList<Integer>) getIntent().getSerializableExtra("image");
        int position = (int) getIntent().getSerializableExtra("position");

        String exerciseName = formatExerciseName(names.get(position));
        titleText.setText(exerciseName);
        titleImage.setImageResource(images.get(position));
        gifImageView.setImageResource(images.get(position));
        gifImageView.setVisibility(View.GONE);
    }

    private String formatExerciseName(String rawName) {
        return rawName.replace("_", " ").replaceAll("\\s+", " ").trim();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupActions() {
        startButton.setOnClickListener(v -> startWorkout());
        stopButton.setOnClickListener(v -> resetWorkoutUi());
    }

    private void startWorkout() {
        if (isRunning) {
            return;
        }
        startButton.setEnabled(false);
        isRunning = true;
        statusText.setText(R.string.session_running);
        statusSubtext.setText("Keep moving with the animation until the final second.");
        celebration.setVisibility(View.GONE);
        celebration.setImageResource(0);
        titleImage.setVisibility(View.GONE);
        gifImageView.setVisibility(View.VISIBLE);

        countDownTimer = new CountDownTimer(TOTAL_DURATION_MS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(formatMillis(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                isRunning = false;
                timer.setText("00:00");
                gifImageView.setVisibility(View.GONE);
                titleImage.setVisibility(View.VISIBLE);
                celebration.setVisibility(View.VISIBLE);
                celebration.setImageResource(R.drawable.celebration);
                statusText.setText(R.string.session_complete);
                statusSubtext.setText("Take a breath, recover, and start again when you're ready.");
                startButton.setEnabled(true);
                showCompletionToast();
            }
        }.start();
    }

    private void resetWorkoutUi() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        isRunning = false;
        startButton.setEnabled(true);
        timer.setText("00:59");
        gifImageView.setVisibility(View.GONE);
        titleImage.setVisibility(View.VISIBLE);
        celebration.setVisibility(View.GONE);
        celebration.setImageResource(0);
        statusText.setText(R.string.session_ready);
        statusSubtext.setText("Press start when you are in position.");
    }

    private String formatMillis(long millisUntilFinished) {
        long totalSeconds = millisUntilFinished / 1000;
        return String.format(Locale.getDefault(), "00:%02d", Math.max(0, totalSeconds));
    }

    public void showCompletionToast() {
        try {
            View layout = LayoutInflater.from(this).inflate(R.layout.toast_custom,
                    (ViewGroup) findViewById(R.id.custom_toast_call));
            ImageView image = layout.findViewById(R.id.image1);
            image.setImageResource(R.drawable.celebration);
            TextView text = layout.findViewById(R.id.text12);
            text.setText("Wooh! You finished the session.");
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void showGuidance() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutSessionActivity.this);
        builder.setMessage("1. Place your phone on a steady surface.\n\n2. Watch the demo movement carefully.\n\n3. Follow the pace for the full minute.");
        builder.setTitle("Session Guidance");
        builder.setCancelable(false);
        builder.setPositiveButton("Let’s go", (DialogInterface.OnClickListener) (dialog, which) -> dialog.cancel());
        builder.setNegativeButton("Go Back", (DialogInterface.OnClickListener) (dialog, which) -> finish());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorAccent);
        }

        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.pinkcolor));

        Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.pinkcolor));
    }

    @Override
    protected void onDestroy() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }
}
