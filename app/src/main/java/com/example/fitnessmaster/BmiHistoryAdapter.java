package com.example.fitnessmaster;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

public class BmiHistoryAdapter extends BaseAdapter {


    private final LayoutInflater inflater;
    private final List<BmiEntry> entries;
 private BmiDao mydb;
 Context context;
 OnItemDeleteListener listener;

    public interface OnItemDeleteListener{
        void onItemDelete(int position);
    }
    public BmiHistoryAdapter(Context context, List<BmiEntry> entries, BmiDao db,OnItemDeleteListener  listener) {
        this.inflater = LayoutInflater.from(context);
        this.entries = entries;
        this.mydb=db;
        this.context=context;
        this.listener=listener;
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

        BmiEntry item = entries.get(position);
        TextView dateView = view.findViewById(R.id.historyDate);
        TextView valueView = view.findViewById(R.id.historyValue);
        TextView statusView = view.findViewById(R.id.historyStatus);
        Button deleteButton = view.findViewById(R.id.deleteEntry);

        dateView.setText(item.getBmidate());
        valueView.setText(String.format(Locale.getDefault(), "BMI %s", item.getBmivalue()));
        statusView.setText(getCategoryText(item.getBmivalue()));
        deleteButton.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder((context));
                builder.setTitle("Delete BMI Entry");
                builder.setMessage("Delete the BMI entry saved for " + entries.get(position).getBmidate() + "?");
                builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) ->deleteEntry(entries.get(position).getId(),position));
                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> dialog.dismiss());
                builder.show();
        });
        return view;
    }

    public void deleteEntry(int entryId, int position) {

        Executors.newSingleThreadExecutor().execute(() -> {

            try {

                mydb.deleteEntry(entryId);

                new Handler(Looper.getMainLooper()).post(() -> {

                    entries.remove(position);

                    notifyDataSetChanged();
                    listener.onItemDelete(position);
                    Toast.makeText(context,
                            "Deleted successfully",
                            Toast.LENGTH_SHORT).show();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
