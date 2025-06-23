package com.example.recyclerapp.Model;

public class FruitItem {

    private String fruitItemTitle;
    private String fruitItemDesc;
    private int fruitItemImage;
    private  boolean favorite;

    public FruitItem(String fruitItemTitle, String fruitItemDesc, int fruitItemImage) {
        this.fruitItemTitle = fruitItemTitle;
        this.fruitItemDesc = fruitItemDesc;
        this.fruitItemImage = fruitItemImage;
        this.favorite = false;
    }

    public FruitItem() {  }

    public String getFruitItemTitle() {
        return fruitItemTitle;
    }

    public void setFruitItemTitle(String fruitItemTitle) {
        this.fruitItemTitle = fruitItemTitle;
    }

    public String getFruitItemDesc() {
        return fruitItemDesc;
    }

    public void setFruitItemDesc(String fruitItemDesc) {
        this.fruitItemDesc = fruitItemDesc;
    }

    public int getFruitItemImage() {
        return fruitItemImage;
    }

    public void setFruitItemImage(int fruitItemImage) {
        this.fruitItemImage = fruitItemImage;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
