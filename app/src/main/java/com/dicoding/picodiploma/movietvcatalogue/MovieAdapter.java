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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movies;
    private OnItemClickListener mListener;

    // Constructor Context
    MovieAdapter(Context context) {
        this.context = context;
    }

    // Setter dan Getter untuk ArrayList
    private ArrayList<Movie> getMovies() {
        return movies;
    }
    void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    // Settingan onClick
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder movieHolder, int i) {
        movieHolder.movTitle.setText(getMovies().get(i).getMvTitle());
        movieHolder.movReleaseDate.setText(getMovies().get(i).getMvReleaseDate());
        movieHolder.movDescription.setText(getMovies().get(i).getMvDescription());

        Glide.with(context)
                .load(getMovies().get(i).getMvPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(movieHolder.movPhoto);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView movPhoto;
        TextView movTitle, movReleaseDate, movDescription;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            movPhoto = itemView.findViewById(R.id.img_mov_photo);
            movTitle = itemView.findViewById(R.id.txt_mov_title);
            movReleaseDate = itemView.findViewById(R.id.txt_mov_release_date);
            movDescription = itemView.findViewById(R.id.txt_mov_description);

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