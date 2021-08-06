package com.example.jobtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements  Itemdetailsadapter.ItemDetailsClickListener {

  private RecyclerView recyclerView;

  EditText itemname;
ArrayList<Itemdetails> itemdetailsArrayList;
  AlertDialog alertDialog;
    Itemdetails itemdetails = new Itemdetails();

    DataBasehelperclass dataBasehelperclass = new DataBasehelperclass(MainActivity.this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add){
          Intent intent = new Intent(this,Additems.class);
          startActivity(intent);
        }
        if(id == R.id.sort){
           showSortDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog(){
        String[] options = {"Alphabetical sorting"};
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort BY");
        builder.setIcon(R.drawable.ic_baseline_sort_24);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               if(which == 0){
                   Collections.sort(itemdetailsArrayList, new Comparator<Itemdetails>() {
                       @Override
                       public int compare(Itemdetails o1, Itemdetails o2) {
                           return o1.Itemname.compareTo(o2.Itemname);
                       }
                   });
               }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        dataBasehelperclass = new DataBasehelperclass(this);
        loadDatatoRecyleview();




        Toolbar toolbar = findViewById(R.id.toolbarscreen);
        setSupportActionBar(toolbar);


    }
    private void loadDatatoRecyleview(){
        ArrayList<Itemdetails> itemdetails = dataBasehelperclass.getDataFromDatabase(dataBasehelperclass.getReadableDatabase());
        Itemdetailsadapter adapter = new Itemdetailsadapter(MainActivity.this,itemdetails);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onEditClicked(Itemdetails edit) {
        AlertDialog.Builder builderobj = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.edit,null);
        EditText itemname = findViewById(R.id.name_of_items_to_be_added);
        itemname.setText(edit.Itemname);

        ImageView closealert = view.findViewById(R.id.close);

        builderobj.setCancelable(false);
        builderobj.setView(view);

        closealert.setOnClickListener(v ->{
            alertDialog.cancel();
        });

        alertDialog = builderobj.create();
        alertDialog.show();
    }


}