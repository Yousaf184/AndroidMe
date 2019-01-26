package com.example.androidme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MasterDetailFragment extends Fragment {

    private static final String KEY_IMAGE_LIST = "image-list";
    private static final String KEY_LIST_INDEX = "image-list-index";

    private List<Integer> imageList;
    private int listIndex;

    public MasterDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState != null) {
            imageList = savedInstanceState.getIntegerArrayList(KEY_IMAGE_LIST);
            listIndex = savedInstanceState.getInt(KEY_LIST_INDEX);
        }

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_master_detail, container, false);

        final ImageView imgView = rootview.findViewById(R.id.body_part_image_view);

        if(imageList != null) {
            imgView.setImageResource(imageList.get(listIndex));

            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listIndex < imageList.size() - 1) {
                        listIndex++;
                    } else {
                        listIndex = 0;
                    }

                    imgView.setImageResource(imageList.get(listIndex));
                }
            });
        }

        return rootview;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(KEY_IMAGE_LIST, (ArrayList<Integer>)imageList);
        outState.putInt(KEY_LIST_INDEX, listIndex);
        super.onSaveInstanceState(outState);
    }

    public void setImageList(List<Integer> imageList) {
        this.imageList = imageList;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
}
