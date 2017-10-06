package com.appsomniac.showbox.adapter.theatre;

/**
 * Created by absolutelysaurabh on 6/10/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.appsomniac.showbox.R;
import com.appsomniac.showbox.activity.more.TheatreActivity;
import com.appsomniac.showbox.model.nearby.PlaceApi;
import com.appsomniac.showbox.model.nearby.Theatre;
import com.appsomniac.showbox.other.Config;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Adapter to display recycler view.
 */
public class TheatreAdapter extends RecyclerView.Adapter<TheatreViewHolder> implements Filterable {

    // Set numbers of Card in RecyclerView.
    private Context context;
    private ArrayList<PlaceApi> al_theatre;
    private ArrayList<PlaceApi> filtered_al_theatre;

    private static int size;

    public TheatreAdapter(Context context, ArrayList<PlaceApi> al_theatre) {

        this.context = context;
        this.al_theatre = al_theatre;
        this.filtered_al_theatre = al_theatre;
    }

    @Override
    public TheatreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TheatreViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(TheatreViewHolder holder, int position) {

        try {

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.superman);
            requestOptions.error(R.drawable.superman);
            String theatre_pic_url="";
            Log.e("al_theatre adapter: ", String.valueOf(filtered_al_theatre.get(position)));

            try {
                theatre_pic_url = Config.theatre_photo_base_url_part_1 + filtered_al_theatre.get(position).getTheatre_image_url().get(0).getTheatre_pic_url() + Config.theatre_photo_base_url_part_2;

            }catch (NullPointerException e){
                e.printStackTrace();
            }
            Log.e("Theatre pic URL: ", theatre_pic_url);

            Glide.with(context).load(theatre_pic_url).apply(requestOptions).thumbnail(0.5f).into(holder.theatre_image);

            holder.theatre_name.setText(filtered_al_theatre.get(position).getTheatre_name());
            holder.theatre_address.setText(filtered_al_theatre.get(position).getTheatre_Address());

            Log.e("position:  AGAIN: ", String.valueOf(position));
        }catch(IndexOutOfBoundsException e){

            e.printStackTrace();
        }
    }

//    public void setFilter(ArrayList<PlaceApi> newList){
//
//        al_theatre = new ArrayList<>();
//        al_theatre.addAll(newList);
//        size = al_theatre.size();
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {

        return filtered_al_theatre.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    filtered_al_theatre = al_theatre;
                } else {

                    ArrayList<PlaceApi> filteredList = new ArrayList<>();

                    for (PlaceApi places : al_theatre) {

                        if (places.getTheatre_name().toLowerCase().contains(charString) || places.getTheatre_Address().toLowerCase().contains(charString)) {

                            filteredList.add(places);
                        }
                    }

                    filtered_al_theatre = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered_al_theatre;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtered_al_theatre = (ArrayList<PlaceApi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
