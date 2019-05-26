package com.katrenich.alex.OsmandTestTask.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.katrenich.alex.OsmandTestTask.R;
import com.katrenich.alex.OsmandTestTask.entities.Region;
import com.katrenich.alex.OsmandTestTask.utils.RegionsXmlParser;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;


public class RegionsListActivity extends AppCompatActivity {
    private Button btnParseXML;
    public static final String TAG = "RegionsListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParseXML = findViewById(R.id.btn_parse);
        btnParseXML.setOnClickListener(this::parseRegionsXML);
    }


    private void parseRegionsXML(View v){
        XmlPullParser parser = getResources().getXml(R.xml.regions);
        List<Region> regions = RegionsXmlParser.parseRegions(parser);
//        Log.i(TAG, "parseRegionsXML: REGIONS: " + regions );

        for (Region r : regions) {
            Log.i(TAG, "parseRegionsXML: REGION:" + r);
        }
    }
}
