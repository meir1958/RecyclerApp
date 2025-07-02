package com.example.recyclerapp.DataBaseFolder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FruitDao {

    @Insert
    void insert(Fruit fruit);

    @Query("SELECT * FROM TblFruit")
    List<Fruit> getAll();

    @Query("SELECT * FROM TblFruit WHERE fruitId==(:fruitId) ")
    Fruit getFruitById(int fruitId);

    @Delete
    void delete(Fruit fruit);

    @Update
    void update(Fruit fruit);


}
