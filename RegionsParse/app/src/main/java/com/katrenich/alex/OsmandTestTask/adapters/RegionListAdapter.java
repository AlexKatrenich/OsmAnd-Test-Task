package com.katrenich.alex.OsmandTestTask.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.katrenich.alex.OsmandTestTask.R;
import com.katrenich.alex.OsmandTestTask.entities.Region;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RegionListAdapter extends RecyclerView.Adapter<RegionListAdapter.RegionViewHolder> {
    private List<Region> mRegions = new ArrayList<>();

    public static final String TAG = "RegionListAdapter";

    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_region_list, viewGroup, false);

        return new RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, int i) {
        holder.bind(mRegions.get(i));
    }

    @Override
    public int getItemCount() {
        return mRegions.size();
    }

    public void setRegions(List<Region> regions) {
        mRegions = regions;
        notifyDataSetChanged();
    }


    // inner view holder
    class RegionViewHolder extends RecyclerView.ViewHolder{
        TextView parentRegionName;
        TextView regionName;

        RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            parentRegionName = itemView.findViewById(R.id.tv_parent_region_name);
            regionName = itemView.findViewById(R.id.tv_region_name);
        }

        void bind(Region region) {
            if (region == null) Log.i(TAG, "bind: region = NULL");
            Region parent = region.getParentRegion();
            if (parent != null) parentRegionName.setText(parent.getName());
            regionName.setText(region.getName());
        }
    }
}
