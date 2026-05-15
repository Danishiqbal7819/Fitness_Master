package com.example.fitnessmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class BmiHistoryAdapter extends BaseAdapter {
    public interface OnDeleteClickListener {
        void onDelete(vendor1 entry);
    }

    private final LayoutInflater inflater;
    private final List<vendor1> entries;
    private final OnDeleteClickListener onDeleteClickListener;

    public BmiHistoryAdapter(Context context, List<vendor1> entries, OnDeleteClickListener onDeleteClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.entries = entries;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_bmi_history, parent, false);
        }

        vendor1 item = entries.get(position);
        TextView dateView = view.findViewById(R.id.historyDate);
        TextView valueView = view.findViewById(R.id.historyValue);
        TextView statusView = view.findViewById(R.id.historyStatus);
        Button deleteButton = view.findViewById(R.id.deleteEntry);

        dateView.setText(item.getName());
        valueView.setText(String.format(Locale.getDefault(), "BMI %s", item.getAge()));
        statusView.setText(getCategoryText(item.getAge()));
        deleteButton.setOnClickListener(v -> onDeleteClickListener.onDelete(item));
        return view;
    }

    private String getCategoryText(String bmiText) {
        try {
            float bmi = Float.parseFloat(bmiText);
            if (bmi < 18.5f) {
                return "Underweight range";
            }
            if (bmi < 25f) {
                return "Healthy range";
            }
            if (bmi < 30f) {
                return "Overweight range";
            }
            return "Obese range";
        } catch (Exception e) {
            return "Saved BMI entry";
        }
    }
}
