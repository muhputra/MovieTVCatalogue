package com.dicoding.picodiploma.movietvcatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVHolder> {
    private Context context;
    private ArrayList<TVShow> tvShows;
    private TVShowAdapter.OnItemClickListener mListener;

    // Constructor Context
    TVShowAdapter(Context context) {
        this.context = context;
    }

    // Setter dan Getter untuk ArrayList
    private ArrayList<TVShow> getTVShows() {
        return tvShows;
    }
    void setTVShows(ArrayList<TVShow> tvShows) {
        this.tvShows = tvShows;
    }

    // Settingan onClick
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    void setOnItemClickListener(TVShowAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TVShowAdapter.TVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tvshow, viewGroup, false);
        return new TVShowAdapter.TVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TVShowAdapter.TVHolder tvHolder, int i) {
        tvHolder.tiviTitle.setText(getTVShows().get(i).getTvTitle());
        tvHolder.tiviReleaseDate.setText(getTVShows().get(i).getTvReleaseDate());
        tvHolder.tiviDescription.setText(getTVShows().get(i).getTvDescription());

        Glide.with(context)
                .load(getTVShows().get(i).getTvPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(tvHolder.tiviPhoto);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TVHolder extends RecyclerView.ViewHolder {
        ImageView tiviPhoto;
        TextView tiviTitle, tiviReleaseDate, tiviDescription;

        TVHolder(@NonNull View itemView) {
            super(itemView);
            tiviPhoto = itemView.findViewById(R.id.img_tivi_photo);
            tiviTitle = itemView.findViewById(R.id.txt_tivi_title);
            tiviReleaseDate = itemView.findViewById(R.id.txt_tivi_release_date);
            tiviDescription = itemView.findViewById(R.id.txt_tivi_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
