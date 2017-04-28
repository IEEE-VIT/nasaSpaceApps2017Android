package com.ieeevit.spaceappsvellore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieeevit.spaceappsvellore.R;
import com.ieeevit.spaceappsvellore.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.MentorViewHolder> {

    List<Result> mentorList;
    Context context;

    public MentorAdapter(List<Result> mentorList, Context context) {
        this.mentorList = mentorList;
        this.context = context;
    }

    @Override
    public MentorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mentor,parent,false);
        return new MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MentorViewHolder holder, int position) {
        holder.name.setText(mentorList.get(position).getName());
        holder.designation.setText(mentorList.get(position).getDesignation());
        holder.skill.setText(mentorList.get(position).getSkill());
        if(!mentorList.get(position).getImage().equals("")) {
            Picasso.with(context).setLoggingEnabled(true);
            Picasso.with(context).load(mentorList.get(position).getImage()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    public static class MentorViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_mentor_name) TextView name;
        @BindView(R.id.tv_mentor_designation) TextView designation;
        @BindView(R.id.tv_mentor_skill) TextView skill;
        @BindView(R.id.iv_mentor_image) ImageView image;

        public MentorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
