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

import org.w3c.dom.Text;

import java.util.List;

public class ItemBikeAdapter extends RecyclerView.Adapter<ItemBikeAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<ItemBike> itemBikes;
    private final ItemBikeAdapter.OnStateClickListener onClickListener;

    public ItemBikeAdapter(Context context, List<ItemBike> itemBikes, OnStateClickListener onClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.itemBikes = itemBikes;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listitem_list_bikes, parent, false);
        return new ItemBikeAdapter.ViewHolder(view);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemBike itemBike = itemBikes.get(position);

        RoomDB db = RoomDB.getInstance(inflater.getContext());
        holder.model.setText(itemBike.getModel());
        holder.name.setText(itemBike.getName());
        holder.price.setText(Integer.toString(itemBike.getPrice())+ MainActivity.currency);
        holder.color.setText(itemBike.getColor());
        holder.brand.setText(itemBike.getBrand());
        if(iterator(db.mainDao().getAll(),itemBike)){
            holder.indicator.setText("✓");
            holder.indicator.setTextColor(R.color.indicator_color);
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                onClickListener.onStateClick(itemBike,position);
                //TO-DO add element to list in basket


                db.mainDao().insert(itemBike);
                holder.indicator.setText("✓");
                holder.indicator.setTextColor(R.color.indicator_color);


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
        final TextView model,name,price,color,brand,indicator;
        final ImageFilterView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageFilterView) itemView.findViewById(R.id.btn_add_to_basket);
            model = (TextView) itemView.findViewById(R.id.modelField);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            color = (TextView) itemView.findViewById(R.id.color);
            brand = (TextView) itemView.findViewById(R.id.brandField);
            indicator= (TextView) itemView.findViewById(R.id.icon_indicator_is_added_tp_basket);
        }
    }
    public boolean iterator (List<ItemBike> list,ItemBike itemBike){
        boolean result=false;
        for (ItemBike item:list) {
            if(item.getId()==itemBike.getId()&&item.getModel().equals(itemBike.getModel())){
                result=true;
            }
        }
        return result;
    }
}
