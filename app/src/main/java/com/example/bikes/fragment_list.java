package com.example.bikes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikes.recyclerViewClass.ItemBike;
import com.example.bikes.recyclerViewClass.ItemBikeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageFilterView btnBasket;
    private static View viewRoot;
    private ArrayList<ItemBike> itemBikes;

    public fragment_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_list.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_list newInstance(String param1, String param2) {
        fragment_list fragment = new fragment_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
  //  @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        btnBasket = getView().findViewById(R.id.buttonTest);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






    }

    public void btnBasket(View view) {
        Navigation.findNavController(view).navigate(R.id.action_fragment_list_to_fragment_basket_list);
    }
    public static void setRecyclerView(){
        MainActivity.selectAllFromDBStore();
        RecyclerView recyclerView = viewRoot.findViewById(R.id.listOfBikes);
        ItemBikeAdapter.OnStateClickListener onStateClickListener = new ItemBikeAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ItemBike itemBike, int position) {

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(viewRoot.getContext()));
        ItemBikeAdapter adapter = new ItemBikeAdapter(viewRoot.getContext(),MainActivity.listItemBikesStore,onStateClickListener);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewRoot =  inflater.inflate(R.layout.fragment_list, container, false);
        btnBasket=   viewRoot.findViewById(R.id.btnImageBasket);
        RecyclerView recyclerView = viewRoot.findViewById(R.id.listOfBikes);


        setRecyclerView();

//        ItemBikeAdapter.OnStateClickListener onStateClickListener = new ItemBikeAdapter.OnStateClickListener() {
//            @Override
//            public void onStateClick(ItemBike itemBike, int position) {
//
//            }
//        };
//        recyclerView.setLayoutManager(new LinearLayoutManager(viewRoot.getContext()));
//        ItemBikeAdapter adapter = new ItemBikeAdapter(viewRoot.getContext(),itemBikes,onStateClickListener);
//        recyclerView.setAdapter(adapter);


        btnBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBasket(view);
            }
        });
        return viewRoot;

    }

}