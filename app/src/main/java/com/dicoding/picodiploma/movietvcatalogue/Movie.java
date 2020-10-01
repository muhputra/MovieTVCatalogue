package com.dicoding.picodiploma.movietvcatalogue;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {
    private String mvTitle;
    private String mvReleaseDate;
    private String mvDescription;
    private int mvPhoto;


    public String getMvTitle() {
        return mvTitle;
    }

    public void setMvTitle(String mvTitle) {
        this.mvTitle = mvTitle;
    }

    public String getMvReleaseDate() {
        return mvReleaseDate;
    }

    public void setMvReleaseDate(String mvReleaseDate) {
        this.mvReleaseDate = mvReleaseDate;
    }

    public String getMvDescription() {
        return mvDescription;
    }

    public void setMvDescription(String mvDescription) {
        this.mvDescription = mvDescription;
    }

    public int getMvPhoto() {
        return mvPhoto;
    }

    public void setMvPhoto(int mvPhoto) {
        this.mvPhoto = mvPhoto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mvPhoto);
        dest.writeString(this.mvTitle);
        dest.writeString(this.mvReleaseDate);
        dest.writeString(this.mvDescription);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.mvTitle = in.readString();
        this.mvReleaseDate = in.readString();
        this.mvDescription = in.readString();
        this.mvPhoto = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}