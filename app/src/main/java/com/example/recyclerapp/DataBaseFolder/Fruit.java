package com.example.recyclerapp.DataBaseFolder;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TblFruit")
public class Fruit {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fruitId")
    private int fruitId;

    @ColumnInfo(name = "fruitName")
    private String fruitName;

    @ColumnInfo(name = "fruitDesc")
    private String fruitDesc;

    @ColumnInfo(name = "fruitPhoto")
    private int fruitPhoto;

    @ColumnInfo(name = "fruitFavorit")
    private  boolean fruitFavorit;

    public Fruit() { }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitDesc() {
        return fruitDesc;
    }

    public void setFruitDesc(String fruitDesc) {
        this.fruitDesc = fruitDesc;
    }

    public int getFruitPhoto() {
        return fruitPhoto;
    }

    public void setFruitPhoto(int fruitPhoto) {
        this.fruitPhoto = fruitPhoto;
    }

    public boolean isFruitFavorit() {
        return fruitFavorit;
    }

    public void setFruitFavorit(boolean fruitFavorit) {
        this.fruitFavorit = fruitFavorit;
    }
}
