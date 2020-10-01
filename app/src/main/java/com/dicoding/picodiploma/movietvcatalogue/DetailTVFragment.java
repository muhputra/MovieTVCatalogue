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


public class DetailTVFragment extends Fragment {
    static String EXTRA_TV = "extra_tv";


    public DetailTVFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi
        TextView detailTvTitle = view.findViewById(R.id.txt_tivi_title);
        TextView detailTvReleaseDate = view.findViewById(R.id.txt_tivi_release_date);
        TextView detailTvDesc = view.findViewById(R.id.txt_tivi_description);
        ImageView detailTvPhoto = view.findViewById(R.id.img_tivi_photo);

        assert getArguments() != null;
        TVShow mTVShow = getArguments().getParcelable(TVShowFragment.EXTRA_TV);

        assert mTVShow != null;
        detailTvTitle.setText(mTVShow.getTvTitle());
        detailTvReleaseDate.setText(mTVShow.getTvReleaseDate());
        detailTvDesc.setText(mTVShow.getTvDescription());
        Glide.with(this).load(mTVShow.getTvPhoto()).into(detailTvPhoto);


    }
}
