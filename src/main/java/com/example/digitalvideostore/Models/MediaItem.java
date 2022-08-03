package com.example.digitalvideostore.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public class MediaItem
{
    //POJO// bean// entity
    @Id
    private String id;
    private String title;
    private String description;
    private String price;
    private String smallPoster;
    private String largePoster;
    private boolean movie;
    private String rentPrice;
    private String purchasePrice;
    private boolean featured;


    public MediaItem() {
    }

    public MediaItem(String id, String title, String description, String price, String smallPoster, String largePoster, boolean movie, String rentPrice, String purchasePrice, boolean featured) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.smallPoster = smallPoster;
        this.largePoster = largePoster;
        this.movie = movie;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.featured = featured;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSmallPoster() {
        return smallPoster;
    }

    public void setSmallPoster(String smallPoster) {
        this.smallPoster = smallPoster;
    }

    public String getLargePoster() {
        return largePoster;
    }

    public void setLargePoster(String largePoster) {
        this.largePoster = largePoster;
    }

    public boolean isMovie() {
        return movie;
    }

    public void setMovie(boolean movie) {
        this.movie = movie;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", smallPoster='" + smallPoster + '\'' +
                ", largePoster='" + largePoster + '\'' +
                ", movie=" + movie +
                ", rentPrice='" + rentPrice + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", featured=" + featured +
                '}';
    }
}
