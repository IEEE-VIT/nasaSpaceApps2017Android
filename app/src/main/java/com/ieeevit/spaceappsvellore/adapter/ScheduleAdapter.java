package com.ieeevit.spaceappsvellore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.ieeevit.spaceappsvellore.R;

/**
 * Created by mayur on 23/04/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    String[] data ;
    String[] time ;
    String[] title;
    Context context;

    public ScheduleAdapter(String[] time,String[] title,String[] data, Context context) {
        this.time = time;
        this.title = title;
        this.data = data;
        this.context = context;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_row,parent,false);
        return new ScheduleViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        holder.textView.setText(data[position]);
        holder.timeView.setText(time[position]);
        holder.titleView.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }


    public static class ScheduleViewHolder extends RecyclerView.ViewHolder{

        TextView textView,timeView,titleView;

        public ScheduleViewHolder(View itemView, int viewType) {
            super(itemView);
            TimelineView mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
            textView = (TextView) itemView.findViewById(R.id.row_text);
            timeView = (TextView) itemView.findViewById(R.id.row_time);
            titleView = (TextView) itemView.findViewById(R.id.row_title);
        }

    }

}
