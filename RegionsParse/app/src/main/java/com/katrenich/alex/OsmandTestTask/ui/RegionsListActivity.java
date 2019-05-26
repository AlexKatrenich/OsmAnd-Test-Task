package com.katrenich.alex.OsmandTestTask.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.katrenich.alex.OsmandTestTask.R;
import com.katrenich.alex.OsmandTestTask.adapters.RegionListAdapter;
import com.katrenich.alex.OsmandTestTask.entities.Region;
import com.katrenich.alex.OsmandTestTask.utils.RegionsXmlParser;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegionsListActivity extends AppCompatActivity {
    public static final String TAG = "RegionsListActivity";

    @BindView(R.id.rv_list_regions)
    protected RecyclerView mRecyclerView;

    protected RegionListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new RegionListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }


    @OnClick(R.id.fab_main)
    protected void parseXml(){
        XmlPullParser parser = getResources().getXml(R.xml.regions);
        List<Region> regions = RegionsXmlParser.parseRegions(parser);
        mAdapter.setRegions(regions);
    }

}
