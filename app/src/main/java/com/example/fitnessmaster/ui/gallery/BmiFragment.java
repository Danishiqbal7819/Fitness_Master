package com.example.fitnessmaster.ui.gallery;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitnessmaster.R;

import java.util.Locale;

public class BmiFragment extends Fragment {
private Button BMICalculate;
private EditText weight;
private EditText height;
private TextView result;
private TextView showtext;

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
                String weightText = weight.getText().toString().trim();
                String heightText = height.getText().toString().trim();
                if (TextUtils.isEmpty(weightText) || TextUtils.isEmpty(heightText)) {
                    Toast.makeText(getContext(),"Enter weight and height first.",Toast.LENGTH_SHORT).show();
                    return;
                }

                    try {
                        float weightValue = getFloatFrom(weightText);
                        float heightValue = getFloatFrom(heightText);
                        if (weightValue <= 0 || heightValue <= 0) {
                            Toast.makeText(getContext(),"Weight and height must be greater than zero.",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        float resultValue=(float)(weightValue /(heightValue*heightValue));
                        result.setText(String.format(Locale.getDefault(),"%.2f", resultValue));
                        if (resultValue < 18.5f){
                            showtext.setText("Underweight range. Add balanced meals and strength work to build healthier momentum.");
                        }
                        else if (resultValue < 25f){
                            showtext.setText("Healthy range. Keep up the consistency with movement, nutrition, and sleep.");
                        }
                        else if (resultValue < 30f){
                            showtext.setText("Overweight range. Regular workouts and a small calorie deficit can help improve this.");
                        }
                        else {
                            showtext.setText("Obese range. Start with sustainable activity and consider expert guidance for a safer plan.");
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(getContext(),"Enter valid numeric values.",Toast.LENGTH_SHORT).show();
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
