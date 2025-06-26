package com.example.recyclerapp;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerapp.Model.AdapterFruit;
import com.example.recyclerapp.Model.FruitItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFruits;
    private AdapterFruit adapter;
    private ArrayList<FruitItem> arrayList;
    private int positionFruit;
    private FruitItem fruitItemRemove;
    private ActionBar actionBar;
    private FloatingActionButton faAddFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("lifecycle","MainActivity onCreate");
        initViews();
        showFrruits();
    }

    private void initViews()
    {
        actionBar = getSupportActionBar();
        rvFruits = findViewById(R.id.rvFruits);
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                positionFruit = position;
                FruitItem fruitItem = arrayList.get(position);
                fruitItemRemove = fruitItem;
//                Snackbar snackbar = Snackbar
//                        .make(rvFruits, "Fruit Remove", Snackbar.LENGTH_LONG)
//                        .setAction("בטל", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                arrayList.add(position, fruitItem);
//                                adapter.notifyItemInserted(position);
//                                //rvFruits.scrollToPosition(position);
//                            }
//                        });
//                snackbar.show();
                if(direction == ItemTouchHelper.LEFT)
                {
                    //adapter.notifyItemChanged(position);
                    //arrayList.remove(position);
                    //adapter.notifyItemRemoved(position);

                    moveLeft();
                }
                else if(direction == ItemTouchHelper.RIGHT)
                {
                    //Toast.makeText(MainActivity.this,  "RIGHT", Toast.LENGTH_SHORT).show();
                    adapter.notifyItemChanged(position);
                    moveRight(fruitItem);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvFruits);

        faAddFruit = findViewById(R.id.faAddFruit);
        faAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFruit();
            }
        });


        setTitle(Html.fromHtml("<font color='#FFFFFF'>RecyclerApp</font>"));
        actionBar.setSubtitle(Html.fromHtml("<font color='#FFFFFF'>Swiped Alert Item</font>"));        // #######################
        //   Keep The Screen 0n
        // #######################
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void addFruit()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_addfruit);
        dialog.setTitle("Login");
        dialog.setCancelable(false);
        EditText etName = dialog.findViewById(R.id.etName);
        EditText etDesc = dialog.findViewById(R.id.etDesc);
        ImageView ivPhoto = dialog.findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(R.drawable.tut);
        ivPhoto.setTag(R.drawable.tut);

        ImageView ivPhoto1 = dialog.findViewById(R.id.ivPhoto1);
        ImageView ivPhoto2 = dialog.findViewById(R.id.ivPhoto2);
        ImageView ivPhoto3 = dialog.findViewById(R.id.ivPhoto3);
        ivPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPhoto.setImageResource(R.drawable.afarsek);
                ivPhoto.setTag(R.drawable.afarsek);
            }
        });
        ivPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPhoto.setImageResource(R.drawable.tut);
                ivPhoto.setTag(R.drawable.tut);
            }
        });
        ivPhoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPhoto.setImageResource(R.drawable.afarsemon);
                ivPhoto.setTag(R.drawable.afarsemon);
            }
        });

        Button btAdd = dialog.findViewById(R.id.btAdd);
        Button btCancel = dialog.findViewById(R.id.btCancel);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                if(name.length() == 0 || desc.length() == 0)
                {
                     Toast.makeText(MainActivity.this,  "מלא פרטים", Toast.LENGTH_SHORT).show();
                }
                else {
                    arrayList.add(new FruitItem(name, desc, (int) ivPhoto.getTag()));
                    adapter.notifyItemChanged(arrayList.size() - 1);
                    dialog.hide();
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });

        dialog.show();

    }
    public void moveRight(FruitItem fruitItem)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_choose);
        dialog.setCancelable(false);
        TextView tvName = dialog.findViewById(R.id.tvName);
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        ImageView ivPhoto = dialog.findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(fruitItem.getFruitItemImage());
        ivPhoto.setTag("" + fruitItem.getFruitItemImage());

        tvName.setText(fruitItem.getFruitItemTitle());
        tvDesc.setText(fruitItem.getFruitItemDesc());
        Button btCancel = dialog.findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        Spinner spinner = dialog.findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<>();
        list.add("בחר אפשרות");
        list.add("שתף עם חבר");
        list.add("חייג מספר");
        list.add("חפש בגוגל");
        list.add("פתח מפה");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                Object x = parent.getItemAtPosition(position);
                String value = x.toString();
                if (value.equals("בחר אפשרות")) {
                    Toast.makeText(MainActivity.this,"בחר אפשרות", Toast.LENGTH_SHORT).show();
                }
                else if (value.equals("שתף עם חבר"))
                {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, " שתף מידע " + fruitItem.getFruitItemTitle());
                    Intent shareIntent = Intent.createChooser(intent, null);
                    startActivity(shareIntent);
                }
                else if (value.equals("חייג מספר"))
                {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return;
                    }
                    else {
                        String phoneNum = "05227248904";
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                        startActivity(intent);
                    }
                }
                else if (value.equals("חפש בגוגל"))
                {
                    String query = fruitItem.getFruitItemTitle();
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/search?q=" +  Uri.encode(query)));
                    startActivity(intent);
                }
                else if (value.equals("פתח מפה"))
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialog.show();
    }

    public void moveLeft()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);// מופע של בילדר
        builder.setTitle("מחיקת פרי");
        builder.setMessage("האם למחוק את הפרי?");
        builder.setCancelable(false);
        builder.setPositiveButton("מסכים", new RemoveItem());
        builder.setNegativeButton("לא מסכים", new RemoveItem());
        AlertDialog dialog = builder.create();// נפעיל את הבילדר ונחזיר רפרנס ל דיאלוג
        dialog.show();
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
    }
    private class RemoveItem implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            String msg = "לא מסכים";
            adapter.notifyItemChanged(positionFruit);
            if(i == -1)// msg = "מסכים";
            {
                Snackbar snackbar = Snackbar
                        .make(rvFruits, "פרי נמחק", Snackbar.LENGTH_LONG)
                        .setDuration(3000)
                        .setBackgroundTint(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setActionTextColor(Color.WHITE)
                        .setAction("בטל פעולה", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                arrayList.add(positionFruit, fruitItemRemove);
                                adapter.notifyItemInserted(positionFruit);
                                rvFruits.scrollToPosition(positionFruit);
                            }
                        });
                snackbar.show();
                arrayList.remove(positionFruit);
                adapter.notifyItemRemoved(positionFruit);
            }
        }
    }

    public void showFrruits()
    {
        arrayList = getArrayList();
        adapter = new AdapterFruit(arrayList);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle","MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle","MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle","MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle","MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle","MainActivity onDestroy");
    }
}