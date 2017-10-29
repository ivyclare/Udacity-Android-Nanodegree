package com.ivy.popularmovies.data.models;

/**
 * Created by Ivoline-Clarisse on 10/28/2017.
 */
import java.util.List;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class MovieModel implements Parcelable {

        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("video")
        @Expose
        private Boolean video;
        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("popularity")
        @Expose
        private Double popularity;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
//        @SerializedName("genre_ids")
//        @Expose
//        private List<Integer> genreIds = null;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("adult")
        @Expose
        private Boolean adult;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        public final static Parcelable.Creator<MovieModel> CREATOR = new Creator<MovieModel>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public MovieModel createFromParcel(Parcel in) {
                return new MovieModel(in);
            }

            public MovieModel[] newArray(int size) {
                return (new MovieModel[size]);
            }

        };

        protected MovieModel(Parcel in) {
            this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
            this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
            this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
            this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
            //in.readList(this.genreIds, (java.lang.Integer.class.getClassLoader()));
            this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
            this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.overview = ((String) in.readValue((String.class.getClassLoader())));
            this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        }

        /**
         * No args constructor for use in serialization
         */
        public MovieModel() {
        }

        /**
         * @param id
         * @param title
         * @param releaseDate
         * @param overview
         * @param posterPath
         * @param originalTitle
         * @param voteAverage
         * @param originalLanguage
         * @param adult
         * @param backdropPath
         * @param voteCount
         * @param video
         * @param popularity
         */
        public MovieModel(Integer voteCount, Integer id, Boolean video, Double voteAverage, String title, Double popularity, String posterPath, String originalLanguage, String originalTitle, String backdropPath, Boolean adult, String overview, String releaseDate) {
            super();
            this.voteCount = voteCount;
            this.id = id;
            this.video = video;
            this.voteAverage = voteAverage;
            this.title = title;
            this.popularity = popularity;
            this.posterPath = posterPath;
            this.originalLanguage = originalLanguage;
            this.originalTitle = originalTitle;
           // this.genreIds = genreIds;
            this.backdropPath = backdropPath;
            this.adult = adult;
            this.overview = overview;
            this.releaseDate = releaseDate;
        }

        public MovieModel(Integer id, String title, String releaseDate, String overview, Double voteAverage, String posterPath, String backdropPath){
            super();

            this.id = id;
            this.title = title;
            this.releaseDate = releaseDate;
            this.overview = overview;
            this.voteAverage = voteAverage;
            this.backdropPath = backdropPath;
            this.posterPath = posterPath;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

//        public List<Integer> getGenreIds() {
//            return genreIds;
//        }
//
//        public void setGenreIds(List<Integer> genreIds) {
//            this.genreIds = genreIds;
//        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(voteCount);
            dest.writeValue(id);
            dest.writeValue(video);
            dest.writeValue(voteAverage);
            dest.writeValue(title);
            dest.writeValue(popularity);
            dest.writeValue(posterPath);
            dest.writeValue(originalLanguage);
            dest.writeValue(originalTitle);
            //dest.writeList(genreIds);
            dest.writeValue(backdropPath);
            dest.writeValue(adult);
            dest.writeValue(overview);
            dest.writeValue(releaseDate);
        }

        public int describeContents() {
            return 0;
        }
    }

