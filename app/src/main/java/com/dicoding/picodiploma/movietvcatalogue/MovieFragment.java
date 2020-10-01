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



public class MovieFragment extends Fragment implements MovieAdapter.OnItemClickListener {
    private RecyclerView rvMovies;
    private ArrayList<Movie> movies = new ArrayList<>();
    private String[] mvTitle, mvReleaseDate, mvDescription;
    private TypedArray mvPhoto;
    static String EXTRA_MOVIE = "extra_movie";


    public MovieFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi disini
        rvMovies = view.findViewById(R.id.rv_movie);
        rvMovies.setHasFixedSize(true);

        prepare();
        addItem();
    }

    private void prepare() {
        mvTitle = getResources().getStringArray(R.array.movie_title);
        mvReleaseDate = getResources().getStringArray(R.array.movie_release_date);
        mvDescription = getResources().getStringArray(R.array.movie_description);
        mvPhoto = getResources().obtainTypedArray(R.array.movie_photo);
    }

    private void addItem() {
        // Panggil item dari string-array ke fragment
        for (int i = 0; i < mvTitle.length; i++) {
            Movie movie = new Movie();
            movie.setMvTitle(mvTitle[i]);
            movie.setMvReleaseDate(mvReleaseDate[i]);
            movie.setMvDescription(mvDescription[i]);
            movie.setMvPhoto(mvPhoto.getResourceId(i, -1));
            movies.add(movie);
        }
        showRecyclerItem();
    }

    private void showRecyclerItem() {
        // set layout manager disini
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter adapter = new MovieAdapter(getContext());
        adapter.setMovies(movies);
        rvMovies.setAdapter(adapter);

        // implementasikan pula onClick
        adapter.setOnItemClickListener(MovieFragment.this);
    }

    @Override
    public void onItemClick(int position) {
        Movie mMovie = new Movie();
        mMovie.setMvTitle(mvTitle[position]);
        mMovie.setMvReleaseDate(mvReleaseDate[position]);
        mMovie.setMvDescription(mvDescription[position]);
        mMovie.setMvPhoto(mvPhoto.getResourceId(position, -1));

        DetailMovieFragment mDetailMovieFragment = new DetailMovieFragment();
        Bundle mbundle = new Bundle();
        mbundle.putParcelable(DetailMovieFragment.EXTRA_MOVIE, mMovie);
        mDetailMovieFragment.setArguments(mbundle);

        FragmentManager mFragmentManager = getFragmentManager();
        assert mFragmentManager != null;
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_layout, mDetailMovieFragment,DetailMovieFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

}