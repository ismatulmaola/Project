package id.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import id.project.AppDatabase;
//import id.project.ListData1;
import id.project.ListData1;
import id.project.R;
import id.project.model.DataModel;
import id.project.model.DataScript;

public class AdapterListBasic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AppDatabase mDb;
    private List<DataModel> items;
    private DatabaseReference mDatabase;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, DataScript obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic(Context context, List<DataModel> items) {
        this.items = items;
        ctx = context;
        mDb = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "project-3ad15-default-rtdb").allowMainThreadQueries().build();
        mOnItemClickListener = (ListData1)context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNISN;
        public TextView txtName1;
        public TextView txtPlaceBirth;
        public TextView txtDateBirth;
        public TextView txtGender;
        public TextView txtStatus;
        public CardView parentLayout;




        public OriginalViewHolder(View v) {
            super(v);
            txtNISN = v.findViewById(R.id.txtNISN);
            txtName1 = v.findViewById(R.id.txtName1);
            txtPlaceBirth= v.findViewById(R.id.txtPlaceBirth);
            txtDateBirth = v.findViewById(R.id.txtDateBirth);
            txtGender = v.findViewById(R.id.txtGender);
            txtStatus = v.findViewById(R.id.txtStatus);
            parentLayout = v.findViewById(R.id.layout_utama);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            OriginalViewHolder view = (OriginalViewHolder) holder;

            DataModel data = items.get(position);
            view.txtNISN.setText(data.getNISN());
            view.txtName1.setText(data.getName());
            view.txtPlaceBirth.setText(data.getPlace_of_birth());
            view.txtDateBirth.setText(data.getDate_birth());
            view.txtGender.setText(data.getGender());
            view.txtStatus.setText(data.getStatus1());
                }
            };

    @Override
    public int getItemCount() {
        return items.size();
    }

}