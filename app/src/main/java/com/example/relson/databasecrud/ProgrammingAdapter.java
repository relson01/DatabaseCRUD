package com.example.relson.databasecrud;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView                 ;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ViewHolder>{


    private ArrayList<String> txtPrice = new ArrayList();
    private ArrayList<String> txtTitle = new ArrayList();
    private  View view;

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



        viewHolder.plus.setVisibility(View.INVISIBLE);
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), txtTitle.get(postion) , Toast.LENGTH_SHORT).show();

            }
        });

        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), txtTitle.get(postion) + " increased", Toast.LENGTH_SHORT).show();
                String s = viewHolder.totalItem.getText().toString();
                int temp  = Integer.parseInt(s);
                temp++;
                s = Integer.toString(temp);
                viewHolder.totalItem.setText(s);

            }
        });

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), txtTitle.get(postion) + " decreased", Toast.LENGTH_SHORT).show();
                String s = viewHolder.totalItem.getText().toString();
                int temp  = Integer.parseInt(s);
                temp--;
                if(temp == 0)
                {
                    viewHolder.addToCart.setVisibility(View.VISIBLE);
                    viewHolder.plus.setVisibility(View.INVISIBLE);
                    viewHolder.minus.setVisibility(View.INVISIBLE);
                    viewHolder.totalItem.setVisibility(View.INVISIBLE);
                }
                else
                {
                    s = Integer.toString(temp);
                    viewHolder.totalItem.setText(s);
                }

            }
        });

        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.addToCart.setVisibility(View.INVISIBLE);
                viewHolder.plus.setVisibility(View.VISIBLE);
                viewHolder.minus.setVisibility(View.VISIBLE);
                viewHolder.totalItem.setVisibility(View.VISIBLE);
            }
        });

      createSnack();

    }

    private void createSnack() {
       // Snackbar snackbar1 = Snackbar.make(R.id.constraintLayout, "Image Deleted!", Snackbar.LENGTH_SHORT);
    }


    @Override
    public int getItemCount() {
        return txtPrice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, price, totalItem;
        ImageView icon;
        RelativeLayout parentLayout;
        Button minus ,plus, addToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.txtItem);
            price = itemView.findViewById(R.id.txtPrice);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            addToCart = itemView.findViewById(R.id.addToCart);
            plus = itemView.findViewById(R.id.imageButtonPlus);
            minus = itemView.findViewById(R.id.imageButtonMinus);
            totalItem = itemView.findViewById(R.id.totalItem);




            /*Glide.with(itemView.getContext())
                    .load(R.drawable.plus)
                    .override(5,10)
                    .into(plus);*/

            /*Glide.with(itemView.getContext())
                    .load(R.drawable.minus)
                    .override(5, 10)
                    .into(minus);*/
        }
    }
}
