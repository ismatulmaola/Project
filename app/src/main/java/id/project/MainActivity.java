package id.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.project.model.DataModel;
import id.project.utility.SharedPrefUtil;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtPlace, txtMom;
    CalendarView calendar;
    Button btnSave, btnSee;
    String date="";
    private AppDatabase mDb;
    private DatabaseReference mDatabase;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName     =findViewById(R.id.txtName);
        txtPlace  =findViewById(R.id.txtPlace);
        txtMom     =findViewById(R.id.txtMom);

        calendar    =findViewById(R.id.calendar);
        btnSave   =findViewById(R.id.btnSave);
        btnSee=findViewById(R.id.btnSee);

        mDb = AppDatabase.getInstance(getApplicationContext());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new Date(year - 1900, month, dayOfMonth);
                date = sdf.format(date1);
                Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
            }
        });

        String dataJson = SharedPrefUtil.getInstance(MainActivity.this).getString("data_input");

        if (!TextUtils.isEmpty(dataJson)) {
        } else {
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void mappingData(String json) {
        DataModel dataModel = new Gson().fromJson(json, DataModel.class);
        txtName.setText(dataModel.getName());
        txtPlace.setText(dataModel.getPlace_of_birth());
        txtMom.setText(dataModel.getMothers_name());

        Date dateDummy = null;
        try {
            dateDummy = new SimpleDateFormat("yyyy-MM-dd").parse(dataModel.getDate_of_birth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setDate(dateDummy.getTime());
    }


    public List<DataModel> getModelArrayString(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<DataModel>>() {}.getType();
        List<DataModel> dataList = gson.fromJson(json, type);
        for (DataModel data : dataList) {
            Log.i("Contact Details", data.getName() + "-" + data.getPlace_of_birth() +  "-" + data.getMothers_name()+  "-" + data.getDate_of_birth());
        }

        return dataList;
    }

    public boolean checkMandatory() {

        boolean pass = true;
        if (TextUtils.isEmpty(txtName.getText().toString())) {
            pass = false;
            txtName.setError("Masukan nama, mandatory");
        }

        return pass;
    }

    private final String status = "0";
    public void save(View view){
        if(checkMandatory()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DataModel data1 = null;
                    mDatabase.child("datanisn").child(generateObjectData().getName()).setValue(generateObjectData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showJsonDialog();
                            }

                            private void showJsonDialog() {
                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                                alertDialog.setTitle("Json");
                                alertDialog.setMessage("Sukses ").setIcon(R.drawable.ic_about).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                AlertDialog alert = alertDialog.create();
                                alert.show();
                            }
                        });
                    }
            }).start();

        }else{
            showErrorDialog();
        }
    }

            public DataModel generateObjectData() {
            DataModel data1 = new DataModel();
            data1.setName(txtName.getText().toString());
            data1.setDate_of_birth(date);
            data1.setPlace_of_birth(txtPlace.getText().toString());
            data1.setMothers_name(txtMom.getText().toString());
            data1.setStatus(status);

            return data1;
            }

            public void showErrorDialog() {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Peringatan");
                alertDialog.setMessage("Mohon isi field yang Mandatory ").setIcon(R.drawable.ic_about).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.show();
            }

        public void seeData(View view){
            Intent intent = new Intent(MainActivity.this, ListData1.class);
            startActivity(intent);
        }

        }
