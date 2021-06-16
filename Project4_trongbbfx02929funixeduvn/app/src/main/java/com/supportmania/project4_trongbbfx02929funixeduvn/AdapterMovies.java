package com.supportmania.project4_trongbbfx02929funixeduvn;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovies extends BaseAdapter {

    private ArrayList<Movies> listMovies;


    public AdapterMovies(ArrayList<Movies> listMovies) {
        this.listMovies = listMovies;
    }

    @Override
    public int getCount() {
        return listMovies.size();
    }

    @Override
    public Object getItem(int i) {
        return listMovies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = View.inflate(viewGroup.getContext(),R.layout.item,null);
        }
        Movies movies = listMovies.get(i);
        ((TextView) view.findViewById(R.id.titleMovie)).setText(movies.getTitle());
        ((TextView) view.findViewById(R.id.priceMovie)).setText(movies.getPrice());
        ImageView imageView = view.findViewById(R.id.imageMovie);
        // get image from json.
        Picasso.get().load(movies.getImageUrl()).into(imageView);
        return view;
    }

}

