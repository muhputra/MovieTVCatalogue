package com.dicoding.picodiploma.movietvcatalogue;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class DetailMovieFragment extends Fragment {
    static String EXTRA_MOVIE = "extra_movie";


    public DetailMovieFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi
        TextView detailMvTitle = view.findViewById(R.id.txt_mov_title);
        TextView detailMvReleaseDate = view.findViewById(R.id.txt_mov_release_date);
        TextView detailMvDesc = view.findViewById(R.id.txt_mov_description);
        ImageView detailMvPhoto = view.findViewById(R.id.img_mov_photo);

        assert getArguments() != null;
        Movie mMovie = getArguments().getParcelable(MovieFragment.EXTRA_MOVIE);

        assert mMovie != null;
        detailMvTitle.setText(mMovie.getMvTitle());
        detailMvReleaseDate.setText(mMovie.getMvReleaseDate());
        detailMvDesc.setText(mMovie.getMvDescription());
        Glide.with(this).load(mMovie.getMvPhoto()).into(detailMvPhoto);
    }
}
