package youtube.demo.youtubedemo;

/**
 * Created by chink on 23/03/2017.
 */


public class Product {

    private int imageId;
    private String title;
    private Double value;

    public Product(int imageId, String title, Double value) {
        this.imageId = imageId;
        this.title = title;
        this.value = value;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getValue() {
        return Double.valueOf(value);
    }

    public void setValue(Double value) {this.value = value;}
}
