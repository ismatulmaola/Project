package id.project.model;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataModel1 {
    @Query("SELECT * FROM DataModel")
    List<DataModel> getAll();

    @Query("SELECT * FROM DataModel WHERE name LIKE '%' || :name || '%'")
    List<DataModel> findByName(String name);

}