package com.example.firebase.ui.slideshow;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.firebase.LoginActivity;
import com.example.firebase.R;

public class LogoutFragment extends Fragment {
    boolean exit;
private Button Yes,No;
    private Button StartButton,StopButton;
    private TextView timer;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        initView(view);
        initClick();
        Logout(view);

        return view;
    }

    private void Logout(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Are You Sure?");
        builder.setTitle("Logging out");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.logout);
        builder.setPositiveButton("Yes",(DialogInterface.OnClickListener)(dialog, which)->{
            sharedPreferences=getContext().getSharedPreferences("Login",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("isLogin",false).apply();
            editor.apply();
            Intent intent=new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(),"Succesfully Logout",Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("cancel",(DialogInterface.OnClickListener)(dialog,which)->{
            dialog.cancel();

        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


    private void initView(View view) {
//        Yes=view.findViewById(R.id.Yes);
//        No=view.findViewById(R.id.NO);
//        StartButton=view.findViewById(R.id.StartButton);
//        StopButton=view.findViewById(R.id.StopButton);
//        timer=view.findViewById(R.id.timer);
    }
    private void initClick() {
//        Yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPreferences=getContext().getSharedPreferences("Login",MODE_PRIVATE);
//                SharedPreferences.Editor editor= sharedPreferences.edit();
//                editor.putBoolean("isLogin",false).apply();
//                editor.apply();
//                Intent intent=new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
//                Toast.makeText(getContext(),"Succesfully Log_out",Toast.LENGTH_SHORT).show();
//            }
//        });
//        No.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"Canceled",Toast.LENGTH_SHORT).show();
//            }
//        });

    }


}