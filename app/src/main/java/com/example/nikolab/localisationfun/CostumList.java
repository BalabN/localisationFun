package com.example.nikolab.localisationfun;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CostumList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Drawable[] imageId;

    public CostumList(Activity context, String[] web, Drawable[] imageId) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        imageView.setImageDrawable(imageId[position]);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.text);
        txtTitle.setText(web[position]);

        return rowView;
    }
}
