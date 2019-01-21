package com.example.relson.databasecrud;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ViewHolder>{


    private ArrayList<String> txtPrice = new ArrayList();
    private ArrayList<String> txtTitle = new ArrayList();


    public ProgrammingAdapter( ArrayList<String> txtTitle, ArrayList<String> txtPrice) {
        Log.d("tag", "ProgrammingAdapter: " + txtPrice);
        this.txtPrice = txtPrice;
        this.txtTitle = txtTitle;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int postion) {
        viewHolder.title.setText(txtTitle.get(postion));
        viewHolder.price.setText(txtPrice.get(postion));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), txtTitle.get(postion) , Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return txtPrice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, price;
        ImageView icon;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.txtItem);
            price = itemView.findViewById(R.id.txtPrice);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
