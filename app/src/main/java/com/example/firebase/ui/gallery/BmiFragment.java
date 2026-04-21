package com.example.firebase.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firebase.R;

public class BmiFragment extends Fragment {
private Button BMICalculate;
private EditText weight;
private EditText height;
private TextView result;
private TextView showtext;
float weight1;
float height1;
float result1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);
        initView(view);
        initClick();

        return view;
    }
    private void initView(View view) {
        BMICalculate= view.findViewById(R.id.BMICalculate);
        weight=(EditText) view.findViewById(R.id.weight);
        height=(EditText) view.findViewById(R.id.height);
        result=view.findViewById(R.id.result);
        showtext=view.findViewById(R.id.Showtext);
    }
    private void initClick() {


        BMICalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a=weight.getText().toString();
                String b=height.getText().toString();
                try {
                    weight1=  getFloatFrom(a);
                    height1=  getFloatFrom(b);

                }
                catch(Exception e){
                    Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
                }

                    try {
                        result1=(float)(weight1 /(height1*height1));
                        result.setText((String.valueOf(result1)));
                        if (result1>18&&result1<25){
                            showtext.setText("your BMI is Normal");
                        }
                        else if (result1>25){
                            showtext.setText("You are considerred as obese\nUse our fitness app to reduce your weight");
                        }
                        else {
                            showtext.setText("you are under weight");
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
                    }
            }
        });
}
    float getFloatFrom(String txt){
        return Float.parseFloat(txt);
    }
//    float getFloatFrom1(EditText txt1){
//        try {
//            return NumberFormat.getInstance().parse(txt1.getText().toString()).floatValue();
//        }
//        catch (ParseException e){
//            return  0.0f;
//        }
//    }

}