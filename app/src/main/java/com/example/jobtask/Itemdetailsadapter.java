package com.example.jobtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Itemdetailsadapter extends RecyclerView.Adapter<Itemdetailsadapter.ItemdetailHolder> {
    @NonNull
    private Context context;
    private ArrayList<Itemdetails> itemdetailsArrayList;
    private ItemDetailsClickListener listener;

    public Itemdetailsadapter(Context context,ArrayList<Itemdetails>itemdetailsArrayList){
        this.context = context;
        this.itemdetailsArrayList = itemdetailsArrayList;
    }
    public void setListener(ItemDetailsClickListener listener){
        this.listener = listener;
    }


    @Override
    public ItemdetailHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(context).inflate(R.layout.cell_items,parent,false);
        ItemdetailHolder holder = new ItemdetailHolder(v1);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Itemdetailsadapter.ItemdetailHolder holder, int position) {
        Itemdetails item = itemdetailsArrayList.get(position);
        holder.itemname.setText(item.Itemname);


        holder.delete.setOnClickListener( v -> {
           itemdetailsArrayList.remove(position);
           notifyDataSetChanged();
        });
       holder.edit.setOnClickListener(v -> {
           listener.onEditClicked(item);
       });

    }

    @Override
    public int getItemCount() {
        return itemdetailsArrayList.size();
    }

    public class ItemdetailHolder extends RecyclerView.ViewHolder {
        private TextView itemname;
        private ImageView delete;
        private  ImageView edit;

        public ItemdetailHolder(@NonNull  View itemView) {


            super(itemView);


            itemname = itemView.findViewById(R.id.items);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
    public interface ItemDetailsClickListener {
        void onEditClicked(Itemdetails edit);



    }
}
