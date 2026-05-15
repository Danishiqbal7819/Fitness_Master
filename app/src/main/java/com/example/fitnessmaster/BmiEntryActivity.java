package com.example.fitnessmaster;

import static android.graphics.Color.BLUE;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class BmiEntryActivity extends AppCompatActivity{
    private Button Save;
    private Button show_data,calculate_BMI;
    private Toolbar toolbar;
    private Button log_out;
    private EditText name;
    private EditText age;
    private TextView BMI_CAL;
    private TextView resultCategory;
    private TextView resultInsight;
    private EditText height,weight;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.showData), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Save=findViewById(R.id.save);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        show_data=findViewById(R.id.show_data);
        log_out=findViewById(R.id.log_out);
        BMI_CAL=findViewById(R.id.BMI_CAL);
        weight=findViewById(R.id.weight);
        height=findViewById(R.id.height);
        calculate_BMI=findViewById(R.id.cal);
        progressBar=findViewById(R.id.progress);
        toolbar=findViewById(R.id.toolbar);
        resultCategory = findViewById(R.id.resultCategory);
        resultInsight = findViewById(R.id.resultInsight);

        log_out.setOnClickListener(v -> Logout());

        BMI_CAL.setOnClickListener(v -> weight.requestFocus());
        calculate_BMI.setOnClickListener(v -> calculateBmi());
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = name.getText().toString().trim();
                String bmi = age.getText().toString().trim();
                if (TextUtils.isEmpty(date) || TextUtils.isEmpty(bmi)){
                    Toast.makeText(BmiEntryActivity.this,"Select a date and calculate or enter BMI first.",Toast.LENGTH_SHORT).show();
                }

                else {


                progressBar.setVisibility(View.VISIBLE);
                HashMap<String,Object> hashMap=new HashMap<String, Object>();

                hashMap.put("name", date);
                hashMap.put("age", bmi);
              FirebaseDatabase.getInstance().getReference().child("vendor1").push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                          progressBar.setVisibility(View.GONE);
                          Toast.makeText(BmiEntryActivity.this,"Succesfully added",Toast.LENGTH_SHORT).show();
                          clearEntryForm();
                      }
                      else {
                          progressBar.setVisibility(View.GONE);
                          Toast.makeText(BmiEntryActivity.this,"Check network connection",Toast.LENGTH_SHORT).show();
                      }
                  }
              });
            }
        } });
        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BmiEntryActivity.this, BmiHistoryActivity.class);
                startActivity(intent);
            }
        });
        
        name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(BmiEntryActivity.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                        name.setText(sdf.format(myCalendar.getTime()));

                    }
                }, mYear, mMonth, mDay);

                mDatePicker.show();
                mDatePicker.getButton(mDatePicker.BUTTON_POSITIVE).setTextColor(BLUE);
                mDatePicker.getButton(mDatePicker.BUTTON_NEGATIVE).setTextColor(BLUE);
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

    private void calculateBmi() {
        String weightText = weight.getText().toString().trim();
        String heightText = height.getText().toString().trim();

        if (TextUtils.isEmpty(weightText) || TextUtils.isEmpty(heightText)) {
            Toast.makeText(this, "Enter weight and height to calculate BMI.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weightValue = Float.parseFloat(weightText);
            float heightValue = Float.parseFloat(heightText);
            if (weightValue <= 0 || heightValue <= 0) {
                Toast.makeText(this, "Weight and height must be greater than zero.", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmiValue = weightValue / (heightValue * heightValue);
            String bmiText = String.format(Locale.getDefault(), "%.2f", bmiValue);
            age.setText(bmiText);
            updateResultState(bmiValue);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter valid numeric values for weight and height.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateResultState(float bmiValue) {
        if (bmiValue < 18.5f) {
            resultCategory.setText("Underweight");
            resultInsight.setText("A nutritious calorie surplus and strength work may help you move toward a healthier range.");
        } else if (bmiValue < 25f) {
            resultCategory.setText("Healthy Range");
            resultInsight.setText("Great balance. Keep supporting it with regular training, sleep, and steady nutrition.");
        } else if (bmiValue < 30f) {
            resultCategory.setText("Overweight");
            resultInsight.setText("A small calorie deficit, consistent walks, and regular workouts can help improve this trend.");
        } else {
            resultCategory.setText("Obese Range");
            resultInsight.setText("Consider gradual lifestyle changes and professional guidance if you want a safer long-term plan.");
        }
    }

    private void clearEntryForm() {
        name.setText("");
        age.setText("");
        weight.setText("");
        height.setText("");
        resultCategory.setText("Your category will appear here");
        resultInsight.setText("Healthy BMI range is usually between 18.5 and 24.9.");
    }

    private void Logout() {
        AlertDialog.Builder builder=new AlertDialog.Builder(BmiEntryActivity.this);
        builder.setMessage("Are You Sure?");
        builder.setTitle("Logging out");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.logout);
        builder.setPositiveButton("Yes",(DialogInterface.OnClickListener)(dialog, which)->{
            Toast.makeText(BmiEntryActivity.this,"Succesfully Logout",Toast.LENGTH_SHORT).show();
            sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("isLogin",false).apply();
            editor.apply();
            Intent intent=new Intent(BmiEntryActivity.this,LoginActivity.class);
            finishAffinity();
            startActivity(intent);
        });
        builder.setNegativeButton("cancel",(DialogInterface.OnClickListener)(dialog,which)->{
            dialog.cancel();

        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
