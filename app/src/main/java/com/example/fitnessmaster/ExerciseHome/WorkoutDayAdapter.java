package com.example.fitnessmaster.ExerciseHome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.fitnessmaster.R;

import java.util.List;

public class WorkoutDayAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<WorkoutDayItem> items;

    public WorkoutDayAdapter(Context context, List<WorkoutDayItem> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return !items.get(position).isHeader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_workout_day, parent, false);
        }

        WorkoutDayItem item = items.get(position);
        TextView badgeView = view.findViewById(R.id.workoutBadge);
        TextView titleView = view.findViewById(R.id.workoutTitle);
        TextView subtitleView = view.findViewById(R.id.workoutSubtitle);
        TextView arrowView = view.findViewById(R.id.workoutArrow);

        badgeView.setText(item.getBadge());
        titleView.setText(item.getTitle());
        subtitleView.setText(item.getSubtitle());

        int background = item.isHeader() ? R.drawable.bg_workout_week_header : R.drawable.bg_workout_list_item;
        view.findViewById(R.id.workoutRow).setBackgroundResource(background);

        if (item.isHeader()) {
            badgeView.setBackgroundResource(R.drawable.bg_workout_badge);
            titleView.setTextSize(20f);
            subtitleView.setTextColor(ContextCompat.getColor(view.getContext(), R.color.fitness_text_secondary));
            arrowView.setVisibility(View.GONE);
        } else {
            badgeView.setBackgroundResource(R.drawable.bg_workout_stat_chip);
            titleView.setTextSize(18f);
            subtitleView.setTextColor(ContextCompat.getColor(view.getContext(), R.color.fitness_text_muted));
            arrowView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public static class WorkoutDayItem {
        private final String badge;
        private final String title;
        private final String subtitle;
        private final boolean header;

        public WorkoutDayItem(String badge, String title, String subtitle, boolean header) {
            this.badge = badge;
            this.title = title;
            this.subtitle = subtitle;
            this.header = header;
        }

        public String getBadge() {
            return badge;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public boolean isHeader() {
            return header;
        }
    }
}
