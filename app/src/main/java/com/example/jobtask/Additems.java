package com.example.jobtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Additems extends AppCompatActivity {
    DataBasehelperclass dataBasehelperclass = new DataBasehelperclass(Additems.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);

        EditText name = findViewById(R.id.name_of_items_to_be_added);


        Button addbtn = findViewById(R.id.add_items);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ittemname = name.getText().toString();


                Itemdetails itemdetails = new Itemdetails();
                itemdetails.Itemname = Ittemname;

               dataBasehelperclass.insertDatatoDatabase(dataBasehelperclass.getWritableDatabase(),itemdetails);

               finish();

            }
        });
    }
}