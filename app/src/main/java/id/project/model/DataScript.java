package id.project.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "DataScript")
public class DataScript {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "NISN")
    private String NISN;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "place_of_birth")
    private String place_of_birth;
    @ColumnInfo(name = "date_birth")
    private String date_birth;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "Status")
    private String Status;

//    @PrimaryKey
//    @NonNull
//    private String key;

    public DataScript(String NISN, String name, String place_of_birth, String date_birth, String gender, String Status) {
    }



    public static void remove(int position) {
    }

    public String getNISN() {
        return NISN;
    }

    public void setNISN(String NISN) {
        this.NISN = NISN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }


}
