package com.example.recyclerapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerapp.Model.AdapterFruit;
import com.example.recyclerapp.Model.FruitItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFruits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        showFrruits();
    }

    private void initViews()
    {
        rvFruits = findViewById(R.id.rvFruits);
    }

    public void showFrruits()
    {
        ArrayList<FruitItem> arrayList = getArrayList();
        AdapterFruit adapter = new AdapterFruit(arrayList);
        rvFruits.setAdapter(adapter);
        rvFruits.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<FruitItem> getArrayList()
    {
        ArrayList<FruitItem> arrayList = new ArrayList<>();
        arrayList.add(new FruitItem("תפוח עץ","פרי הדר",R.drawable.apple));
        arrayList.add(new FruitItem("תפוז","פרי הדר",R.drawable.orange));
        arrayList.add(new FruitItem("מנגו","פרי טרופי",R.drawable.mango));
        arrayList.add(new FruitItem("ליצי","פרי טרופי",R.drawable.lichi));
        arrayList.add(new FruitItem("אגס","פרי הדר",R.drawable.pear));

        return arrayList;
    }
}