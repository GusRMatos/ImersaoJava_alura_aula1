package model;

public class Content {
    private String title;
    private String urlImage;
    private String rating;

    public Content(String title, String urlImage, String rating) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
    }
    public Content(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
    }
    public String getTitle() {
        return title;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public String getRating(){return rating;}


}
