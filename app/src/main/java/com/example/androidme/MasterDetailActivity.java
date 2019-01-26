package com.example.androidme;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MasterDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);

        if(savedInstanceState == null) {

            Intent intent = getIntent();
            Bundle b = intent.getBundleExtra(MasterListActivity.KEY_DATA_BUNDLE);

            int headPartIndex = b.getInt(MasterListActivity.KEY_HEAD_PART_INDEX);
            int bodyPartIndex = b.getInt(MasterListActivity.KEY_BODY_PART_INDEX);
            int legPartIndex = b.getInt(MasterListActivity.KEY_LEGS_PART_INDEX);

            MasterDetailFragment headFragment = new MasterDetailFragment();
            headFragment.setImageList(ImageAssetsUtil.getHeads());
            headFragment.setListIndex(headPartIndex);

            MasterDetailFragment bodyFragment = new MasterDetailFragment();
            bodyFragment.setImageList(ImageAssetsUtil.getBodies());
            bodyFragment.setListIndex(bodyPartIndex);

            MasterDetailFragment legsFragment = new MasterDetailFragment();
            legsFragment.setImageList(ImageAssetsUtil.getLegs());
            legsFragment.setListIndex(legPartIndex);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }
    }
}
