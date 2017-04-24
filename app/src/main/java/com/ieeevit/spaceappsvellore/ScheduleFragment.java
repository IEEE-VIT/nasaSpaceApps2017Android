package com.ieeevit.spaceappsvellore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeevit.spaceappsvellore.adapter.ScheduleAdapter;


public class ScheduleFragment extends Fragment{

    String[] time = {"8:00 am","10:00 am","11:00 am","1:00 pm","2:00 pm","4:00 pm","8:00 pm","1:00 am","6:00 am", "9:00 am","11:00 am","2:00 pm","5:00 pm","6:00 pm"};
    String[] title = {"Registration Check","HACK101","Hacking Begins","Lunch","HackShop Opens","Open Mentor Hours","Dinner","Midnight Munchies","Team Acknowledgement","Breakfast","Practice Presentations","Final Presentations","Judges Deliberation and Feedback Session","Closing Ceremony and Results"};
    String[] data = {"All the Registered Participants acknowledge there presence followed by simultaneous Ice-Breaking Sessions.","NASA SpaceApps 2017 would be explained and problem statements would be discussed. All the participants would be introduced to NASA Open Data and the judging criteria.","Participants are expected to get a sense of problem statements by now. Hack Begins. Participants would be given HackerSpace, some plug points and a good WiFi Connection.","Take a break, Relax!","Hardware components will be given to the Teams, if required, on a First-Come-First-Serve basis.","Mentors from diverse backgrounds would be accessible to all the participants.","Fuel Up!","Snacks and Caffeine will be provided to the hackers to keep up with the energy!","Final Team List would be prepared for scheduling the presentations.","Relax For a While!","Time will be given to better help the hackers with their own mock presentation.","The time for the participants to Show Off their hard work done during the SpaceAppsChallenge!","Participants will be reminded to focus on giving kind, specific and helpful feedback.","The most awaited moment!"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rv_schedule);
        ScheduleAdapter adapter = new ScheduleAdapter(time,title,data,getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        return v;
    }
}