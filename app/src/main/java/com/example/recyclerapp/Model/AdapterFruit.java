package com.example.recyclerapp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerapp.DataBaseFolder.Fruit;
import com.example.recyclerapp.DataBaseFolder.FruitDatabase;
import com.example.recyclerapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterFruit extends RecyclerView.Adapter<AdapterFruit.MyViewHolder> {

    private Context context;
    private List<Fruit> list;
    private FruitDatabase database;

    public AdapterFruit(Context context,List<Fruit> list)
    {
        this.context = context;
        this.list = list;
        database = FruitDatabase.getDatabase(context);
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

        Fruit fruit = list.get(position);
        holder.ivFruit.setImageResource(fruit.getFruitPhoto());
        holder.tvTitle.setText(fruit.getFruitName());
        holder.tvDesc.setText(fruit.getFruitDesc());
        boolean newFavorite = fruit.isFruitFavorit();

        if(!newFavorite)holder.ivHurt.setImageResource(R.drawable.hurt_white);
        else holder.ivHurt.setImageResource(R.drawable.hurt_red);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean newFavorite = fruitItem.isFavorite();
//                fruitItem.setFavorite(!newFavorite);
//                notifyItemChanged(holder.getAdapterPosition());
//            }
//        });
        holder.ivHurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fruit fruit1 = database.fruitDao().getFruitById(fruit.getFruitId());
                boolean isFavorit = fruit1.isFruitFavorit();
                fruit1.setFruitFavorit(!isFavorit);
                database.fruitDao().update(fruit1);

                fruit.setFruitFavorit(!newFavorite);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
