package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;

    Button addButton, deleteButton, confirmButton; // declare button variables
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText editCity;
    int selectedIndex = -1; // tp keep track of city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.button_add);
        deleteButton = findViewById(R.id.button_del);
        confirmButton = findViewById(R.id.button_confirm);
        editCity = findViewById(R.id.add_city);

        String []cities = {"Edmonton", "Vancouver", "moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing","Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList); // this references the current activity
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;// city to delete
            Toast.makeText(MainActivity.this, "City: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });

        addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 editCity.setVisibility(View.VISIBLE);
                 confirmButton.setVisibility(View.VISIBLE);
                 addButton.setVisibility(View.GONE);
                 deleteButton.setVisibility(View.GONE);
                 editCity.requestFocus();

                 //dataList.add("New City");// change to put whatever is in edit text view
                 //

             }
         });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = editCity.getText().toString();
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                editCity.setText("");

                editCity.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
                addButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (selectedIndex == -1) {
                        Toast.makeText(MainActivity.this, "Tap a city first", Toast.LENGTH_SHORT).show(); // learned from lab demo
                    return;}
                    String removed = dataList.remove(selectedIndex);
                    cityAdapter.notifyDataSetChanged();
            }
        });

    }
}