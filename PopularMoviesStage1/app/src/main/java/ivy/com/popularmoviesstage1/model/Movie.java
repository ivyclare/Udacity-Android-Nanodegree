package ivy.com.popularmoviesstage1.model;

public class Movie {
    private String image;
    private String title;
    private String overview;
    private String average;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAverage() {

        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getOverview() {

        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Movie() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
