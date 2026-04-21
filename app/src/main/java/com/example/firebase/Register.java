package com.example.firebase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private Button registerd;
    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        registerd=findViewById(R.id.registerd);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();

         registerd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email1=email.getText().toString();
                 String password1=password.getText().toString();
                 if(TextUtils.isEmpty(email1)||TextUtils.isEmpty(password1)){
                     Toast.makeText(Register.this,"no data",Toast.LENGTH_SHORT).show();
                 }
                 else {
                     regis(email1,password1);
                 }

             }
         });
    }
    private void regis(String email1, String password1) {
   auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
       @Override
       public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Toast.makeText(Register.this,"succesfull",Toast.LENGTH_SHORT).show();
           }
           else {
               Toast.makeText(Register.this,"failed",Toast.LENGTH_SHORT).show();
           }
       }
   });
    }
}