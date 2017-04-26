package com.ieeevit.spaceappsvellore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeevit.spaceappsvellore.adapter.MentorAdapter;
import com.ieeevit.spaceappsvellore.models.MentorsResponse;
import com.ieeevit.spaceappsvellore.models.Result;
import com.ieeevit.spaceappsvellore.utility.Consts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MentorFragment extends Fragment {

    @BindView(R.id.rv_mentor)
    RecyclerView mentor;

    private MentorsResponse mentors;

    public MentorFragment() {}

    public static MentorFragment newInstance(MentorsResponse mentors) {
        MentorFragment fragment = new MentorFragment();
        Bundle args = new Bundle();
        args.putSerializable(Consts.MENTOR, mentors);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mentors = (MentorsResponse) getArguments().getSerializable(Consts.MENTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mentor, container, false);
        ButterKnife.bind(this, v);

        mentor.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mentor.setAdapter(new MentorAdapter(mentors.getResult(), getContext()));

        return v;
    }

}
