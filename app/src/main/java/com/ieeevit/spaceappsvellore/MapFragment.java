package com.ieeevit.spaceappsvellore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MapFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.map_image);
        Glide.with(getActivity()).load("https://maps.googleapis.com/maps/api/staticmap?center=12.9713985,79.1611101&markers=color:red|Clabel:S|12.970804,79.159444&zoom=16&size=400x400&maptype=roadmap&key=AIzaSyCg5Rj6aA_69lLQHNYW6JccKpPKddGpE2c").into(imageView);

        double latitude = 12.970804;
        double longitude = 79.1611101;
        String label = "Technology Tower";
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        final Uri uri = Uri.parse(uriString);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        return v;
    }
}