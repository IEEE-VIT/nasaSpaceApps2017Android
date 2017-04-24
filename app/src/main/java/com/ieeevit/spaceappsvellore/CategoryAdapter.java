package com.ieeevit.spaceappsvellore;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeevit.spaceappsvellore.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ProfileViewHolder> {

    private List<Category> categoryList;
    private int rowLayout;
    private Activity context;

    static class ProfileViewHolder extends RecyclerView.ViewHolder {

        ProfileViewHolder(View v) {
            super(v);
        }
    }

    public CategoryAdapter(List<Category> categoryList, int rowLayout, Activity context) {
        this.categoryList = categoryList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}