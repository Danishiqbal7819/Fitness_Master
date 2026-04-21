package com.example.firebase;

import static android.graphics.Color.BLUE;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import androidx.cardview.widget.CardView;
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

public class MainActivity2 extends AppCompatActivity{
    private Button Save;
    private Button show_data,calculate_BMI;
    private Toolbar toolbar;
    private Button log_out;
    private TextView name;
    private EditText age;
    private TextView BMI_CAL;
    private EditText height,weight;
    private ProgressBar progressBar;
    CardView card;
    float weight1;
    float height1;
    float result1;
    private boolean isLogin;
   private SharedPreferences sharedPreferences;
private  FirebaseDatabase firebaseDatabase;
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
        card=findViewById(R.id .card);
        progressBar=findViewById(R.id.progress);
        toolbar=findViewById(R.id.toolbar);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

        BMI_CAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    card.setVisibility(View.VISIBLE);
                    calculate_BMI.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String a=weight.getText().toString();
                            String b=height.getText().toString();
                            try {
                                weight1=  getFloatFrom(a);
                                height1=  getFloatFrom(b);

                            }
                            catch(Exception e){
                                Toast.makeText(MainActivity2.this,""+e,Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(MainActivity2.this,"click",Toast.LENGTH_SHORT).show();

                            try {
                                result1=(float)(weight1 /(height1*height1));
                                age.setText((String.valueOf(result1)));

                            }
                            catch (Exception e){
                                Toast.makeText(MainActivity2.this,""+e,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                catch (Exception e){
                    Toast.makeText(MainActivity2.this,""+e,Toast.LENGTH_SHORT).show();
                }

            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")||age.getText().toString().equals("")){
                    Toast.makeText(MainActivity2.this,"Empty",Toast.LENGTH_SHORT).show();
                }

                else {


                progressBar.setVisibility(View.VISIBLE);
                HashMap<String,Object> hashMap=new HashMap<String, Object>();

                hashMap.put("name",name.getText().toString());
                hashMap.put("age",age.getText().toString());
              FirebaseDatabase.getInstance().getReference().child("vendor1").push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                          progressBar.setVisibility(View.GONE);
                          Toast.makeText(MainActivity2.this,"Succesfully added",Toast.LENGTH_SHORT).show();
                      }
                      else {
                          progressBar.setVisibility(View.GONE);
                          Toast.makeText(MainActivity2.this,"Check network connection",Toast.LENGTH_SHORT).show();
                      }
                  }
              });
            }
        } });
        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
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

                DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity2.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
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
    float getFloatFrom(String txt){
        return Float.parseFloat(txt);
    }

    private void Logout() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity2.this);
        builder.setMessage("Are You Sure?");
        builder.setTitle("Logging out");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.logout);
        builder.setPositiveButton("Yes",(DialogInterface.OnClickListener)(dialog, which)->{
            Toast.makeText(MainActivity2.this,"Succesfully Logout",Toast.LENGTH_SHORT).show();
            sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("isLogin",false).apply();
            editor.apply();
            Intent intent=new Intent(MainActivity2.this,LoginActivity.class);
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