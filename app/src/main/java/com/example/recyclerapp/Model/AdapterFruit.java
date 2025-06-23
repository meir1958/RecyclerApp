package com.example.recyclerapp.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerapp.R;

import java.util.ArrayList;

public class AdapterFruit extends RecyclerView.Adapter<AdapterFruit.MyViewHolder> {

    private ArrayList<FruitItem> arrayList;

    public AdapterFruit(ArrayList<FruitItem> arrayList)
    {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_fruit, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FruitItem fruitItem = arrayList.get(position);
        holder.ivFruit.setImageResource(fruitItem.getFruitItemImage());
        holder.tvTitle.setText(fruitItem.getFruitItemTitle());
        holder.tvDesc.setText(fruitItem.getFruitItemDesc());
        boolean newFavorite = fruitItem.isFavorite();

        if(!newFavorite)holder.ivHurt.setImageResource(R.drawable.hurt_white);
        else holder.ivHurt.setImageResource(R.drawable.hurt_red);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newFavorite = fruitItem.isFavorite();
                fruitItem.setFavorite(!newFavorite);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final ImageView ivFruit;
        public final TextView tvTitle;
        public final TextView tvDesc;
        public final ImageView ivHurt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFruit = itemView.findViewById(R.id.ivFruit);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivHurt = itemView.findViewById(R.id.ivHurt);

        }
    }
}
