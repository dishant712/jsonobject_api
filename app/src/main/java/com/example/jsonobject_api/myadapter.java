package com.example.jsonobject_api;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myclass> {


    MainActivity mainActivity;
    ArrayList idList,titleList,descList,priceList,disperList,ratingList,stockList,brandList,categoryList,thumbList,images;

    public myadapter(MainActivity mainActivity, ArrayList idList, ArrayList titleList, ArrayList descList, ArrayList priceList, ArrayList disperList, ArrayList ratingList, ArrayList stockList, ArrayList brandList, ArrayList categoryList, ArrayList thumbList) {
        this.mainActivity=mainActivity;
        this.idList=idList;
        this.titleList=titleList;
        this.descList=descList;
        this.priceList=priceList;
        this.disperList=disperList;
        this.ratingList=ratingList;
        this.stockList=stockList;
        this.brandList=brandList;
        this.categoryList=categoryList;
        this.thumbList=thumbList;
        this.images=images;

    }

    @NonNull
    @Override
    public myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        myclass m = new myclass(view);
        return m;
    }

    class myclass extends RecyclerView.ViewHolder{

        TextView id,title,desc,price,disper,rating,stock,brand,category;
        ImageView thumb,images;

        public myclass(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.item_id);
            title=itemView.findViewById(R.id.item_title);
            desc=itemView.findViewById(R.id.item_desc);
            price=itemView.findViewById(R.id.item_price);
            disper=itemView.findViewById(R.id.item_disprice);
            rating=itemView.findViewById(R.id.item_rating);
            stock=itemView.findViewById(R.id.item_stock);
            brand=itemView.findViewById(R.id.item_brand);
            category=itemView.findViewById(R.id.item_category);
            thumb=itemView.findViewById(R.id.thumb);
            images=itemView.findViewById(R.id.img);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull myclass holder, int position) {


             holder.id.setText("Id = "+(int) idList.get(position));
             holder.title.setText("Title = "+(CharSequence) titleList.get(position));
             holder.desc.setText("Description = "+(CharSequence) descList.get(position));
             holder.price.setText("Price = "+(int) priceList.get(position));
             holder.disper.setText("Discount Price = "+(int) disperList.get(position));
             holder.rating.setText("Rating = "+(int) ratingList.get(position));
             holder.stock.setText("Stock = "+(int) stockList.get(position));
             holder.brand.setText("Brand = "+(CharSequence) brandList.get(position));
             holder.category.setText("Category = "+(CharSequence) categoryList.get(position));

             Glide.with(mainActivity).load(thumbList.get(position)).placeholder(R.drawable.load).into(holder.thumb);

        holder.thumb.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, MainActivity2.class);
            intent.putExtra("id", (Integer) idList.get(holder.getAdapterPosition()));
            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}
