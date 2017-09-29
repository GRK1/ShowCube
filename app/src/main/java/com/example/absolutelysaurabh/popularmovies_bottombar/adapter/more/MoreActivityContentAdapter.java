package com.example.absolutelysaurabh.popularmovies_bottombar.adapter.more;

/**
 * Created by absolutelysaurabh on 23/8/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.absolutelysaurabh.popularmovies_bottombar.R;
import com.example.absolutelysaurabh.popularmovies_bottombar.base.SplashActivity;
import com.example.absolutelysaurabh.popularmovies_bottombar.model.Movie;
import com.example.absolutelysaurabh.popularmovies_bottombar.viewHolder.more.MoreActivityViewHolder;

/**
 * Adapter to display recycler view.
 */
public class MoreActivityContentAdapter extends RecyclerView.Adapter<MoreActivityViewHolder> {

    private Context context;
    private int section_position;

    public MoreActivityContentAdapter(Context context, int section_position) {

        this.context = context;
        this.section_position = section_position;
    }

    @Override
    public MoreActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MoreActivityViewHolder(LayoutInflater.from(parent.getContext()), parent, section_position);
    }

    @Override
    public void onBindViewHolder(MoreActivityViewHolder holder, int position) {

        try {

            Log.e("Section POSITION: ", String.valueOf(section_position));
            Log.e("POSITION: ", String.valueOf(position));

            String posterBaseUrl = "http://image.tmdb.org/t/p/w185/"+SplashActivity.allMovieSampleData.get(section_position)
                    .getAllItemsInSection().get(position).getPosterPath();

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.spectre);
            requestOptions.error(R.drawable.spectre);

            Log.e("Movie arrayList size:  ", String.valueOf(SplashActivity.allMovieSampleData.get(section_position)
                    .getAllItemsInSection().size()));

            Log.e("POSTERURL: ", posterBaseUrl);

            try {

                Glide.with(context).load(posterBaseUrl).apply(requestOptions).thumbnail(0.5f).into(holder.picture);
            }catch(NullPointerException n){
                n.printStackTrace();
            }

            holder.title.setText(SplashActivity.allMovieSampleData.get(section_position)
                    .getAllItemsInSection().get(position).getTitle());

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }
    @Override
    public int getItemCount() {
        return SplashActivity.allMovieSampleData.get(section_position).getAllItemsInSection().size();
    }
}