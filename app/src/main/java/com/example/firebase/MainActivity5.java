package com.example.firebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity5 extends AppCompatActivity {

    private TextView Title_text,statustask;
private GifImageView gifImageView,statusgif,celebration;
private ImageView title_image;
RelativeLayout relativeLayout;
private Button  StopButton,StartButton,CustomMessge;
    private TextView timer;
    Toast toast;
    boolean exit;
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


        Title_text=findViewById(R.id.title_text);
        gifImageView=findViewById(R.id.title_gif);
        timer=findViewById(R.id.timer);
        StartButton=findViewById(R.id.StartButton);
        StopButton=findViewById(R.id.StopButton);
        statustask=findViewById(R.id.statustask);
        relativeLayout=findViewById(R.id.main);
        celebration=findViewById(R.id.celebration);
        title_image=findViewById(R.id.title_image);
        Intent intent=getIntent();
        ArrayList<String> filelist =  (ArrayList<String>)getIntent().getSerializableExtra("name");
        ArrayList<Integer> filelist1 =  (ArrayList<Integer>)getIntent().getSerializableExtra("image");

        int i= (int) getIntent().getSerializableExtra("position");

            Title_text.setText(filelist.get(i));
            title_image.setImageResource(filelist1.get(i));
            gifImageView.setImageResource(filelist1.get(i));
            gifImageView.setVisibility(View.GONE);

            noitify();

            StartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    StartButton.setEnabled(false);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                statustask.setText("");
                gifImageView.setVisibility(View.VISIBLE);
                exit=false;
                celebration.setImageResource(0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int x=59;
                        while(x>=0) {
                            if(x==0){
                                try {
                                    statustask.setText("OOHOO!! Task Completed");

                                    Toastmessge();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            if (exit==false){
                                timer.setText("00:"+String.valueOf(x));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e){
                                    throw new RuntimeException(e);
                                }
                                x--;
                            }
                            else {
                                break;
                            }

                            StopButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Thread.interrupted();
                                    gifImageView.setVisibility(View.GONE);
                                    title_image.setVisibility(View.VISIBLE);
                                    StartButton.setEnabled(true);
                                    exit=true;
                                    timer.setText("00:59");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
    public  void Toastmessge(){
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.setText("00:00");
                        gifImageView.setVisibility(View.GONE);
                        title_image.setVisibility(View.VISIBLE);
                        celebration.setImageResource(R.drawable.celebration);
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_custom,
                                (ViewGroup) findViewById(R.id.custom_toast_call));
                        ImageView image = (ImageView) layout.findViewById(R.id.image1);
                        image.setImageResource(R.drawable.celebration);
                        TextView text = (TextView) layout.findViewById(R.id.text12);
                        text.setText("Wooh!! You have done the task");
//                        Toast toast = new Toast(getApplicationContext());
//                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                        toast.setDuration(Toast.LENGTH_LONG);
//                        toast.setView(layout);
//                        toast.show();
                    }
                });
            }
            catch (Exception e){
                Toast.makeText(getApplication(),""+e,Toast.LENGTH_SHORT).show();
            }

        }

        private void noitify(){

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity5.this);
            builder.setMessage("Step1:Put Your Phone at the Surface." +
                    "Step2:Take Look into the phone." +
                    "Step3:Follow the instruction of the person for the given amount of time.");
            builder.setTitle("Guidance");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok",(DialogInterface.OnClickListener)(dialog,which)->{
                dialog.cancel();
            });
            builder.setNegativeButton("Go_Back",(DialogInterface.OnClickListener)(dialog,which)->{
                finish();
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

            alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorAccent);


            Button buttonPositive=alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonPositive.setTextColor(ContextCompat.getColor(this,R.color.pinkcolor));

            Button buttonnegative=alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            buttonnegative.setTextColor(ContextCompat.getColor(this,R.color.pinkcolor));

        }
}