package com.example.bikes.recyclerViewClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikes.DataBaseModule.RoomDB;
import com.example.bikes.MainActivity;
import com.example.bikes.R;

import java.util.List;

public class ItemBikeBasketAdapter extends RecyclerView.Adapter<ItemBikeBasketAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<ItemBike> itemBikes;
    private final ItemBikeBasketAdapter.OnStateClickListener onClickListener;

    public ItemBikeBasketAdapter(Context context, List<ItemBike> itemBikes, OnStateClickListener onClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.itemBikes = itemBikes;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listitem_basket_bikes, parent, false);
        return new ItemBikeBasketAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemBike itemBike = itemBikes.get(position);
        holder.model.setText(itemBike.getModel());
        holder.name.setText(itemBike.getName());
        holder.price.setText(Integer.toString(itemBike.getPrice())+ MainActivity.currency);
        holder.color.setText(itemBike.getColor());
        holder.brand.setText(itemBike.getBrand());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onStateClick(itemBike,position);
                //TO-DO remove element from db


//

               // MainActivity.listItemBikesBasket.clear();
               // MainActivity.listItemBikesBasket=db.mainDao().getAll();
              //  MainActivity.removeItemFromBDBasket(itemBike);
               // MainActivity.selectAllFromDBBasket();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemBikes.size();
    }
    public interface OnStateClickListener{
        void onStateClick(ItemBike itemBike, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView model,name,price,color,brand;
        final ImageFilterView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageFilterView)itemView.findViewById(R.id.btn_remove_item_basket);
            model = (TextView) itemView.findViewById(R.id.modelField);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            color = (TextView) itemView.findViewById(R.id.color);
            brand = (TextView) itemView.findViewById(R.id.brandField);
        }
    }
}
