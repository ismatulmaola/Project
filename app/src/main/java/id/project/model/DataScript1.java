package id.project.model;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataScript1 {
    @Query("SELECT * FROM DataScript")
    List<DataScript> getAll();

}
