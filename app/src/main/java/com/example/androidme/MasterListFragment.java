package com.example.androidme;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class MasterListFragment extends Fragment {

    private ImageClickListener imgClickListener;

    public MasterListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_master_list, container, false);

        ListAdapter adapter = new ListAdapter(getContext(), ImageAssetsUtil.getAll());

        GridView gridView = rootview.findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgClickListener.imageClicked(position);
            }
        });

        return rootview;
    }

    public interface ImageClickListener {
        void imageClicked(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            imgClickListener = (ImageClickListener) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }
}
