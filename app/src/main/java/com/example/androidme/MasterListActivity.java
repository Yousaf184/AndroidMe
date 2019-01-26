package com.example.androidme;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MasterListActivity extends AppCompatActivity
                                              implements MasterListFragment.ImageClickListener {

    private int headPartIndex;
    private int bodyPartIndex;
    private int legsPartIndex;

    public static final String KEY_HEAD_PART_INDEX = "head-index";
    public static final String KEY_BODY_PART_INDEX = "body-index";
    public static final String KEY_LEGS_PART_INDEX = "legs-index";
    public static final String KEY_DATA_BUNDLE = "data-bundle";

    private boolean inTwoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list);

        // android_me_linear_layout will not exists in single pane mode
        inTwoPaneMode = findViewById(R.id.android_me_linear_layout) != null;

        if(inTwoPaneMode) {

            if(savedInstanceState != null) {
                headPartIndex = savedInstanceState.getInt(KEY_HEAD_PART_INDEX);
                bodyPartIndex = savedInstanceState.getInt(KEY_BODY_PART_INDEX);
                legsPartIndex = savedInstanceState.getInt(KEY_LEGS_PART_INDEX);
            }

            Button btn = findViewById(R.id.open_master_detail_btn);
            btn.setVisibility(View.GONE);

            GridView gridView = findViewById(R.id.gridview);
            gridView.setNumColumns(2);

            MasterDetailFragment headFragment = new MasterDetailFragment();
            headFragment.setImageList(ImageAssetsUtil.getHeads());
            headFragment.setListIndex(headPartIndex);

            MasterDetailFragment bodyFragment = new MasterDetailFragment();
            bodyFragment.setImageList(ImageAssetsUtil.getBodies());
            bodyFragment.setListIndex(bodyPartIndex);

            MasterDetailFragment legsFragment = new MasterDetailFragment();
            legsFragment.setImageList(ImageAssetsUtil.getLegs());
            legsFragment.setListIndex(legsPartIndex);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }
    }

    @Override
    public void imageClicked(int position) {
        // body part number will be 0 for head part, 1 for body part and 2 for legs part
        int bodyPartNumber = position / 12;
        int index = position - (12 * bodyPartNumber);

        switch (bodyPartNumber) {
            case 0:
                headPartIndex = index;
                break;
            case 1:
                bodyPartIndex = index;
                break;
            case 2:
                legsPartIndex = index;
                break;
            default:
                break;
        }

        if(inTwoPaneMode) {

            MasterDetailFragment fragment = new MasterDetailFragment();
            int fragmentContainerToReplace = -1;

            switch (bodyPartNumber) {
                case 0:
                    fragment.setImageList(ImageAssetsUtil.getHeads());
                    fragment.setListIndex(headPartIndex);
                    fragmentContainerToReplace = R.id.head_container;
                    break;
                case 1:
                    fragment.setImageList(ImageAssetsUtil.getBodies());
                    fragment.setListIndex(bodyPartIndex);
                    fragmentContainerToReplace = R.id.body_container;
                    break;
                case 2:
                    fragment.setImageList(ImageAssetsUtil.getLegs());
                    fragment.setListIndex(legsPartIndex);
                    fragmentContainerToReplace = R.id.legs_container;
                    break;
                default:
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                                       .replace(fragmentContainerToReplace, fragment)
                                       .commit();
        }
    }

    public void seeDetails(View view) {
        Bundle b = new Bundle();

        b.putInt(KEY_HEAD_PART_INDEX, headPartIndex);
        b.putInt(KEY_BODY_PART_INDEX, bodyPartIndex);
        b.putInt(KEY_LEGS_PART_INDEX, legsPartIndex);

        Intent openDetailsActivity = new Intent(this, MasterDetailActivity.class);
        openDetailsActivity.putExtra(KEY_DATA_BUNDLE, b);

        startActivity(openDetailsActivity);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(inTwoPaneMode) {
            outState.putInt(KEY_HEAD_PART_INDEX, headPartIndex);
            outState.putInt(KEY_BODY_PART_INDEX, bodyPartIndex);
            outState.putInt(KEY_LEGS_PART_INDEX, legsPartIndex);
        }
    }
}
