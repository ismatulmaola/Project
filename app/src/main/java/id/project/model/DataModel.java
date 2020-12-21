package id.project.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DataModel")
public class DataModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "NISN")
    private String NISN;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "place_of_birth")
    private String place_of_birth;
    @ColumnInfo(name = "date_of_birth")
    private String date_of_birth;
    @ColumnInfo(name = "date_birth")
    private String date_birth;
    @ColumnInfo(name = "mothers_name")
    private String mothers_name;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "status1")
    private String status1;
    @ColumnInfo(name = "Status")
    private String Status;

//    @PrimaryKey
//    @NonNull
//    private String key;

    public DataModel(String name, String place_of_birth, String date_of_birth, String mothers_name, String status) {
    }

    public DataModel() {

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

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
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


    public String getMothers_name() {
        return mothers_name;
    }

    public void setMothers_name(String mothers_name) {
        this.mothers_name = mothers_name;
    }

    public String getStatus() {
        return status1;
    }

    public void setStatus(String status) {
        this.status1 = status;
    }
//
    public String getStatus1() {
        return Status;
    }

    public void setStatus1(String Status) {
        this.Status = Status;
    }

//    public String getKey() {
//        return key;
//    }
//    public void setKey (String key){
//        this.key=key;
//    }
}

