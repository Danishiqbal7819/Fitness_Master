package com.example.firebase.ui.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firebase.R;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class QuoteFragment extends Fragment {

    TextView tv_text;
   Button refreshButton;
    Button shareButton;
    int  i;
    LinkedList<String> arrayList1;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_quote, container, false);

        initView(view);
        initClick();

        return view;
    }
    private void initView(View view) {
        tv_text=view.findViewById(R.id.tv_text);
   refreshButton=view.findViewById(R.id.refresh);
        shareButton =view.findViewById(R.id.Share);
    }

    private void initClick() {


        arrayList1=new LinkedList<String>();
        arrayList1.add(getString(R.string.quote_string1));
        arrayList1.add(getString(R.string.quote_string2));
        arrayList1.add(getString(R.string.quote_string3));
        arrayList1.add(getString(R.string.quote_string4));
        arrayList1.add(getString(R.string.quote_string5));
        arrayList1.add(getString(R.string.quote_string6));


       i = ThreadLocalRandom.current().nextInt(0, arrayList1.size());
        quote(arrayList1,tv_text,i);

        refreshButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            if (i==arrayList1.size()){
                i=0;
            }
            else {

                tv_text.setText(arrayList1.get(i++));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
        }
    }
});
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent= new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/palin");
                shareIntent.putExtra(Intent.EXTRA_TEXT,tv_text.getText().toString());
                startActivity(Intent.createChooser(shareIntent,"share this quote"));

            }

        });

    }


    private  void quote(LinkedList<String> arrayList, TextView tv_text, int i) {

        tv_text.setText(arrayList.get(i));
    }


}