package id.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import id.project.adapter.AdapterListBasic;
import id.project.model.DataModel;
import id.project.model.DataScript;
import id.project.utility.SharedPrefUtil;

public class ListData1 extends AppCompatActivity implements AdapterListBasic.OnItemClickListener {

    RecyclerView lstBiodata;
    private List<DataModel> biodataList;
    private AppDatabase mDb;
    private DatabaseReference mDbFirebase;
    private Button btnCari;
    private EditText txtCari;
    private AdapterListBasic adapter;
    String textCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lstBiodata = findViewById(R.id.lstdata);
        lstBiodata.setHasFixedSize(true);
        lstBiodata.setLayoutManager(new LinearLayoutManager(this));
        mDbFirebase = FirebaseDatabase.getInstance().getReference("data_scrap");
        mDb = AppDatabase.getInstance(getApplicationContext());

        loadDataFirebase();

    }

    public List<DataModel> loadData(){
        List<DataModel> biodataList = null;
        String dataJson = SharedPrefUtil.getInstance(ListData1.this).getString("data_input");
        if (!dataJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<DataModel>>() {}.getType();
            biodataList = gson.fromJson(dataJson, type);
            for (DataModel data : biodataList) {
                Log.i("Contact Details", data.getName() + "-" + data.getPlace_of_birth() + "-" + data.getStatus());
            }
        }

        return biodataList;
    }

    public void loadDatabase(){
        List<DataModel> dataList = null;
        dataList = mDb.dataScrap().getAll();
        adapter = new AdapterListBasic(ListData1.this, biodataList);
        adapter.setOnItemClickListener(ListData1.this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lstBiodata.setLayoutManager(new LinearLayoutManager(ListData1.this));
                lstBiodata.setItemAnimator(new DefaultItemAnimator());
                lstBiodata.setAdapter(adapter);
            }
        });
    }


    public void loadDataFirebase(){
        mDbFirebase = FirebaseDatabase.getInstance().getReference("data_scrap");
        mDbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    biodataList = new ArrayList<>();
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        DataModel b = npsnapshot.getValue(DataModel.class);
                        biodataList.add(b);
                    }
                    adapter = new AdapterListBasic(ListData1.this,biodataList);
                    lstBiodata.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lstBiodata.setLayoutManager(new LinearLayoutManager(ListData1.this));
                        lstBiodata.setItemAnimator(new DefaultItemAnimator());
                        lstBiodata.setAdapter(adapter);
                    }});
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadDataFirebase();
    }

    @Override
    public void onItemClick(View view, DataScript obj, int position) {

    }
}