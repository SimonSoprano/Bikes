package com.example.bikes;

import android.os.Bundle;

import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bikes.DataBaseModule.RoomDB;
import com.example.bikes.recyclerViewClass.ItemBike;
import com.example.bikes.recyclerViewClass.ItemBikeBasketAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_basket_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_basket_list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static View viewRoot;
    private ImageFilterView btnOutBasket;
    private RecyclerView recyclerView;
    private static TextView sumValueInBasket;
    private List<ItemBike> itemBikeList= new ArrayList<>();
    private RoomDB db;

    public fragment_basket_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_basket_list.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_basket_list newInstance(String param1, String param2) {
        fragment_basket_list fragment = new fragment_basket_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void btnOutBasket(View view){
        //go to next fragment
        Navigation.findNavController(view).navigate(R.id.action_fragment_basket_list_to_fragment_list);
    }
    protected void setRecyclerView(){
        //reset recycler view wth reseting list
        itemBikeList.clear();

        itemBikeList= db.mainDao().getAll();
        sumValueInBasket.setText(Integer.toString(sumCounter(itemBikeList)));

        RecyclerView recyclerView = viewRoot.findViewById(R.id.listOfBikes_basket);
        ItemBikeBasketAdapter.OnStateClickListener onStateClickListener = new ItemBikeBasketAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ItemBike itemBike, int position) {

                db.mainDao().delete(itemBike);
                itemBikeList.clear();
                itemBikeList= db.mainDao().getAll();
                setRecyclerView();
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(viewRoot.getContext()));
        ItemBikeBasketAdapter adapter = new ItemBikeBasketAdapter(viewRoot.getContext()
                ,itemBikeList,onStateClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewRoot=inflater.inflate(R.layout.fragment_basket_list, container, false);
        btnOutBasket= viewRoot.findViewById(R.id.btnOutBacket);
        recyclerView = viewRoot.findViewById(R.id.listOfBikes_basket);
        db = RoomDB.getInstance(viewRoot.getContext());
        sumValueInBasket = (TextView)viewRoot.findViewById(R.id.valueToPay_Field);
        sumValueInBasket.setText(Integer.toString(sumCounter(MainActivity.listItemBikesBasket)));

        setRecyclerView();






        btnOutBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOutBasket(view);
            }
        });

        return viewRoot;
    }
    public int sumCounter(List<ItemBike> list){
        int result = 0;
        for (ItemBike item:list) {
            result = item.getPrice()+ result;
        }
        return result;
    }
}