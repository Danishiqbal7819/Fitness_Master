package com.example.firebase.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firebase.ExerciseHome.FitnessActivity;
import com.example.firebase.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Integer> piclist=new ArrayList<>();
    ArrayList<String> Strings1=new ArrayList<>();
    ArrayList<String> Strings2=new ArrayList<>();
    ArrayList<String> Strings3=new ArrayList<>();
    ArrayList<Integer> list1=new ArrayList<>();
    ArrayList<Integer> list2=new ArrayList<>();
    ArrayList<Integer> list3=new ArrayList<>();
    private TextView titleText1,titleText2,titleText3,titleText4,titleText5,titleText6;
 private ArrayList<String>imageUrls;
private ImageView img1,img2,img3,img4,img5,img6;


    StorageReference root;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        initClick();

        return view;
}

    private void initClick() {

        String TitleText1=titleText1.getText().toString().trim();
        String TitleText2=titleText2.getText().toString().trim();
        String TitleText3=titleText3.getText().toString().trim();
        String TitleText4=titleText4.getText().toString().trim();
        String TitleText5=titleText5.getText().toString().trim();
        String TitleText6=titleText6.getText().toString().trim();

        FirebaseApp.initializeApp(getContext());
        progressBar.setVisibility(View.GONE);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Strings1.clear();
                Strings2.clear();
                Strings3.clear();
                list1.clear();
                list2.clear();
                list3.clear();

                Strings1.add("Side_To_Side_Turns");
                Strings1.add("Tongue_Turning");
                Strings1.add("Neck_Lift");
                Strings1.add("Full_Face_Massage");
                Strings1.add("LION");


                Strings2.add("Side_To_Side_Turns");
                Strings2.add("LION");
                Strings2.add("Neck_Lift");
                Strings2.add("Tongue_Turning");
                Strings2.add("Full_Face_Massage");

                Strings3.add("Side_To_Side_Turns");
                Strings3.add("Neck_Lift");
                Strings3.add("LION");
                Strings3.add("Tongue_Turning");
                Strings3.add("Full_Face_Massage");


                list1.add(R.drawable.mew1);
                list1.add(R.drawable.mew2);
                list1.add(R.drawable.mew3);
                list1.add(R.drawable.mew4);
                list1.add(R.drawable.mew5);

                list2.add(R.drawable.mew1);
                list2.add(R.drawable.mew5);
                list2.add(R.drawable.mew3);
                list2.add(R.drawable.mew2);
                list2.add(R.drawable.mew4);

                list3.add(R.drawable.mew1);
                list3.add(R.drawable.mew3);
                list3.add(R.drawable.mew5);
                list3.add(R.drawable.mew2);
                list3.add(R.drawable.mew4);

                Intent intent=new Intent(getContext(), FitnessActivity.class);
                intent.putExtra("name1",Strings1);
                intent.putExtra("pic1",list1);
                intent.putExtra("name2",Strings2);
                intent.putExtra("pic2",list2);
                intent.putExtra("name3",Strings3);
                intent.putExtra("pic3",list3);
                intent.putExtra("title",TitleText1);
                startActivity(intent);

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Strings1.clear();
                Strings2.clear();
                Strings3.clear();
                list1.clear();
                list2.clear();
                list3.clear();


                Strings1.add("Standing criss cross crunches");
                Strings1.add(" Bent leg jackknife");
                Strings1.add(" Triangle crunch");
                Strings1.add(" Plank hip dips");
                Strings1.add(" Side leg lifts");


                Strings2.add("Bent leg jackknife");
                Strings2.add("Standing criss cross crunches");
                Strings2.add(" Plank hip dips");
                Strings2.add(" Triangle crunch");
                Strings2.add(" Side leg lifts");


                Strings3.add("Bent leg jackknife");
                Strings3.add("Standing criss cross crunches");
                Strings3.add(" Plank hip dips");
                Strings3.add(" Triangle crunch");
                Strings3.add(" Side leg lifts");

                list1.add(R.drawable.standingcrisscross);
                list1.add(R.drawable.bentlegjackknife);
                list1.add(R.drawable.trianglecrunchexerciseillustrationspotebi);
                list1.add(R.drawable.plankhipdipsexerciseillustration);
                list1.add(R.drawable.sidelegliftsexerciseillustrationspotebi);


                list2.add(R.drawable.bentlegjackknife);
                list2.add(R.drawable.standingcrisscross);
                list2.add(R.drawable.plankhipdipsexerciseillustration);
                list2.add(R.drawable.trianglecrunchexerciseillustrationspotebi);
                list2.add(R.drawable.sidelegliftsexerciseillustrationspotebi);


                list3.add(R.drawable.bentlegjackknife);
                list3.add(R.drawable.standingcrisscross);
                list3.add(R.drawable.plankhipdipsexerciseillustration);
                list3.add(R.drawable.trianglecrunchexerciseillustrationspotebi);
                list3.add(R.drawable.sidelegliftsexerciseillustrationspotebi);


                    progressBar.setVisibility(View.GONE);
                    Intent intent=new Intent(getContext(), FitnessActivity.class);
                    intent.putExtra("name1",Strings1);
                    intent.putExtra("pic1",list1);
                    intent.putExtra("name2",Strings2);
                    intent.putExtra("pic2",list2);
                    intent.putExtra("name3",Strings3);
                    intent.putExtra("pic3",list3);
                    intent.putExtra("title",TitleText2);
                    startActivity(intent);

            }
        });


        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Strings1.clear();
                Strings2.clear();
                Strings3.clear();
                list1.clear();
                list2.clear();
                list3.clear();

                Strings1.add("Classic_Pushup");
                Strings1.add("Close_Grip Pushup");
                Strings1.add("Diamond_Pushup");
                Strings1.add("Decline Pushup");
                Strings1.add("Clock Pushup");
                Strings1.add("Pushup Medicine Ball");


                Strings2.add("Diamond_Pushup");
                Strings2.add("Decline Pushup");
                Strings2.add("Shoulder Trap Pushup Polymetrics");
                Strings2.add("Single Arm Pushup");
                Strings2.add("Superman_Pushup");
                Strings2.add("Assisted_Weighted_Pushup");

                Strings3.add("Close_Grip_Pushup");
                Strings3.add("Decline_Pushup");
                Strings3.add("Clock_Pushup");
                Strings3.add("Pushup_Medicine_Ball");
                Strings3.add("Shoulder_Trap_pushup_Polymetrics");
                Strings3.add("Single_Arm_Pushup");

                list1.add(R.drawable.pic1);
                list1.add(R.drawable.pic2);
                list1.add(R.drawable.pic3);
                list1.add(R.drawable.pic4);
                list1.add(R.drawable.pic5);
                list1.add(R.drawable.pic6);

                list2.add(R.drawable.pic3);
                list2.add(R.drawable.pic4);
                list2.add(R.drawable.pic7);
                list2.add(R.drawable.pic8);
                list2.add(R.drawable.pic9);
                list2.add(R.drawable.pic10);

                list3.add(R.drawable.pic2);
                list3.add(R.drawable.pic4);
                list3.add(R.drawable.pic5);
                list3.add(R.drawable.pic6);
                list3.add(R.drawable.pic7);
                list3.add(R.drawable.pic8);


                Intent intent=new Intent(getContext(), FitnessActivity.class);
                intent.putExtra("name1",Strings1);
                intent.putExtra("pic1",list1);
                intent.putExtra("name2",Strings2);
                intent.putExtra("pic2",list2);
                intent.putExtra("name3",Strings3);
                intent.putExtra("pic3",list3);
                intent.putExtra("title",TitleText3);
                startActivity(intent);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Strings1.clear();
                Strings2.clear();
                Strings3.clear();
                list1.clear();
                list2.clear();
                list3.clear();

                Strings1.clear();
                Strings2.clear();
                Strings3.clear();
                list1.clear();
                list2.clear();
                list3.clear();

                Strings1.add(" Jump rope");
                Strings1.add(" Run in place");
                Strings1.add(" Butt kicks");
                Strings1.add(" Mountain climbers");
                Strings1.add(". Speed bag punches");

                list1.add(R.drawable.jump);
                list1.add(R.drawable.run);
                list1.add(R.drawable.buttkicks);
                list1.add(R.drawable.mountain);
                list1.add(R.drawable.speed);

                Strings2.add(" Butt kicks");
                Strings2.add(" Run in place");
                Strings2.add(" Mountain climbers");
                Strings2.add(". Speed bag punches");
                Strings2.add(" Jump rope");


                list2.add(R.drawable.buttkicks);
                list2.add(R.drawable.run);
                list2.add(R.drawable.mountain);
                list2.add(R.drawable.speed);
                list2.add(R.drawable.jump);


                Strings3.add(" Run in place");
                Strings3.add(" Mountain climbers");
                Strings3.add(" Jump rope");
                Strings3.add(". Speed bag punches");
                Strings3.add(" Butt kicks");


                ;
                list3.add(R.drawable.run);
                list3.add(R.drawable.mountain);
                list3.add(R.drawable.jump);
                list3.add(R.drawable.speed);
                list3.add(R.drawable.buttkicks);



                Intent intent=new Intent(getContext(), FitnessActivity.class);
                intent.putExtra("name1",Strings1);
                intent.putExtra("pic1",list1);
                intent.putExtra("name2",Strings2);
                intent.putExtra("pic2",list2);
                intent.putExtra("name3",Strings3);
                intent.putExtra("pic3",list3);
                intent.putExtra("title",TitleText4);
                startActivity(intent);

            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   try {
       String url = "https://www.livofy.com/health/gym-diet-plan/".trim();
       Intent i = new Intent(Intent.ACTION_VIEW);
       i.setData(Uri.parse(url));
       startActivity(i);
      }
   catch (Exception e){
    Toast.makeText(getContext(),"unable to show nutrion"+e,Toast.LENGTH_SHORT).show();
}

            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Strings1.clear();
                Strings2.clear();
                Strings3.clear();
                list1.clear();
                list2.clear();
                list3.clear();


                Strings1.add("Mountain pose");
                Strings1.add(" Standing half forward bend pose");
                Strings1.add(" Chair pose");
                Strings1.add(" Warrior II pose");
                Strings1.add(" Warrior I pose");

                list1.add(R.drawable.mountainpose);
                list1.add(R.drawable.standingpose);
                list1.add(R.drawable.utkatasana);
                list1.add(R.drawable.warrior);
                list1.add(R.drawable.warriorpose);



                Strings2.add(" Standing half forward bend pose");
                Strings2.add(" Chair pose");
                Strings2.add("Mountain pose");
                Strings2.add(" Warrior I pose");
                Strings2.add(" Warrior II pose");

                list2.add(R.drawable.standingpose);
                list2.add(R.drawable.standingpose);
                list2.add(R.drawable.mountainpose);
                list2.add(R.drawable.utkatasana);
                list2.add(R.drawable.warriorpose);
                list2.add(R.drawable.warrior);



                Strings3.add("Mountain pose");
                Strings3.add(" Chair pose");
                Strings3.add(" Standing half forward bend pose");
                Strings3.add(" Warrior II pose");
                Strings3.add(" Warrior I pose");

                list3.add(R.drawable.mountainpose);
                list3.add(R.drawable.utkatasana);
                list3.add(R.drawable.standingpose);
                list3.add(R.drawable.warrior);
                list3.add(R.drawable.warriorpose);



                Intent intent=new Intent(getContext(), FitnessActivity.class);
                intent.putExtra("name1",Strings1);
                intent.putExtra("pic1",list1);
                intent.putExtra("name2",Strings2);
                intent.putExtra("pic2",list2);
                intent.putExtra("name3",Strings3);
                intent.putExtra("pic3",list3);
                intent.putExtra("title",TitleText6);
                startActivity(intent);

            }
        });

    }

    private void initView(View view) {
        img1=view.findViewById(R.id.img1);
        img2=view.findViewById(R.id.img2);
        img3=view.findViewById(R.id.img3);
        img4=view.findViewById(R.id.img4);
        img5=view.findViewById(R.id.img5);
        img6=view.findViewById(R.id.img6);
        titleText1=view.findViewById(R.id.titleText1);
        titleText2=view.findViewById(R.id.titleText2);
        titleText3=view.findViewById(R.id.titleText3);
        titleText4=view.findViewById(R.id.titleText4);
        titleText5=view.findViewById(R.id.titleText5);
        titleText6=view.findViewById(R.id.titleText6);
        progressBar=view.findViewById(R.id.progress);

    }

}