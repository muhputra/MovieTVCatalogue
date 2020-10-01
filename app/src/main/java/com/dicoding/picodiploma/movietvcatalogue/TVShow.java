package com.dicoding.picodiploma.movietvcatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String tvTitle;
    private String tvReleaseDate;
    private String tvDescription;
    private int tvPhoto;

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvReleaseDate() {
        return tvReleaseDate;
    }

    public void setTvReleaseDate(String tvReleaseDate) {
        this.tvReleaseDate = tvReleaseDate;
    }

    public String getTvDescription() {
        return tvDescription;
    }

    public void setTvDescription(String tvDescription) {
        this.tvDescription = tvDescription;
    }

    public int getTvPhoto() {
        return tvPhoto;
    }

    public void setTvPhoto(int tvPhoto) {
        this.tvPhoto = tvPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tvTitle);
        dest.writeString(this.tvReleaseDate);
        dest.writeString(this.tvDescription);
        dest.writeInt(this.tvPhoto);
    }

    public TVShow() {
    }

    protected TVShow(Parcel in) {
        this.tvTitle = in.readString();
        this.tvReleaseDate = in.readString();
        this.tvDescription = in.readString();
        this.tvPhoto = in.readInt();
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
