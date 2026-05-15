package com.example.fitnessmaster.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessmaster.WorkoutSessionActivity;
import com.example.fitnessmaster.R;

import java.util.ArrayList;

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        Context context;
        ArrayList<String> Strings;
        ArrayList<Integer> list;
//        ArrayList<String> list1;
        public Adapter(Context context, ArrayList<String> Strings, ArrayList<Integer> list){
            this.context=context;
            this.Strings=Strings;
            this.list=list;
        }
        @NonNull
        @Override
        public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View view=inflater.inflate(R.layout.listitem,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            holder.text.setText(Strings.get(position));
            holder.imageView.setImageResource(list.get(position));
            holder.badge.setText("Step " + (position + 1));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, WorkoutSessionActivity.class);
                    intent.putExtra("name",Strings);
                    intent.putExtra("image",list);
                    intent.putExtra("position",holder.getAdapterPosition());
                    v.getContext().startActivity(intent);

                }
            });
        }
        @Override
        public int getItemCount() {
            return Strings.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView text;
            TextView badge;
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text1);
                badge = itemView.findViewById(R.id.stepBadge);
                imageView = itemView.findViewById(R.id.img1);
            }
        }}

