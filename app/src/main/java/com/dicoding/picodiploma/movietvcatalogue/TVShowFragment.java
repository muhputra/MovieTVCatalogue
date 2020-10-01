package com.dicoding.picodiploma.movietvcatalogue;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



public class TVShowFragment extends Fragment implements TVShowAdapter.OnItemClickListener {
    private RecyclerView rvTVShows;
    private ArrayList<TVShow> TVShows = new ArrayList<>();
    private String[] tvTitle, tvReleaseDate, tvDescription;
    private TypedArray tvPhoto;
    static String EXTRA_TV = "extra_tv";


    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi disini
        rvTVShows = view.findViewById(R.id.rv_tvshow);
        rvTVShows.setHasFixedSize(true);

        tvTitle = getResources().getStringArray(R.array.tvshow_title);
        tvReleaseDate = getResources().getStringArray(R.array.tvshow_release_date);
        tvDescription = getResources().getStringArray(R.array.tvshow_description);
        tvPhoto = getResources().obtainTypedArray(R.array.tvshow_photo);

        // Panggil item dari string-array ke fragment
        for (int i = 0; i < tvTitle.length; i++) {
            TVShow tvShow = new TVShow();
            tvShow.setTvTitle(tvTitle[i]);
            tvShow.setTvReleaseDate(tvReleaseDate[i]);
            tvShow.setTvDescription(tvDescription[i]);
            tvShow.setTvPhoto(tvPhoto.getResourceId(i, -1));
            TVShows.add(tvShow);
            showRecyclerItem();
        }

    }

    private void showRecyclerItem() {
        // set layout manager disini
        rvTVShows.setLayoutManager(new LinearLayoutManager(getActivity()));
        TVShowAdapter adapter = new TVShowAdapter(getContext());
        adapter.setTVShows(TVShows);
        rvTVShows.setAdapter(adapter);

        // implementasikan pula onClick
        adapter.setOnItemClickListener(TVShowFragment.this);
    }

    @Override
    public void onItemClick(int position) {
        TVShow mTVShow = new TVShow();
        mTVShow.setTvTitle(tvTitle[position]);
        mTVShow.setTvReleaseDate(tvReleaseDate[position]);
        mTVShow.setTvDescription(tvDescription[position]);
        mTVShow.setTvPhoto(tvPhoto.getResourceId(position, -1));

        DetailTVFragment mDetailTVFragment = new DetailTVFragment();
        Bundle mbundle = new Bundle();
        mbundle.putParcelable(DetailTVFragment.EXTRA_TV, mTVShow);
        mDetailTVFragment.setArguments(mbundle);

        FragmentManager mFragmentManager = getFragmentManager();
        assert mFragmentManager != null;
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_layout, mDetailTVFragment,DetailTVFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

}