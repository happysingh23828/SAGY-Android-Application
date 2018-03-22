package dynamicdrillers.sagy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentActivitiesFragment extends Fragment {
    RecyclerView rv=null;
    DatabaseReference mDatabase;


    public RecentActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_activities, container, false);


        rv = (RecyclerView) view.findViewById(R.id.recent_recycler);
//        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(linearLayoutManager);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("activities");
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Query query = mDatabase;

        FirebaseRecyclerOptions<ModelRecent> options = new FirebaseRecyclerOptions.Builder<ModelRecent>()
                .setQuery(query,ModelRecent.class)
                .build();
        FirebaseRecyclerAdapter<ModelRecent ,RecentActivityViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelRecent, RecentActivityViewHolder>
                (options) {
            @Override
            protected void onBindViewHolder(@NonNull RecentActivityViewHolder holder, int position, @NonNull ModelRecent model) {
                holder.setTitle(model.getTitle());
                holder.setDescription(model.getDescription());
            }

            @Override
            public RecentActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view =  LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recent_activity_item, parent, false);
                return new RecentActivityViewHolder(view);
            }
        };
        rv.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }


    public  static class RecentActivityViewHolder  extends RecyclerView.ViewHolder{
        View mView;


        public RecentActivityViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }
        public void setTitle(String title) {
            TextView mtitle = mView.findViewById(R.id.activity_title);
            mtitle.setText(title);
        }

        public void setDescription(String description) {
            TextView mdesc = mView.findViewById(R.id.activity_desc);
            mdesc.setText(description);
        }


    }
}
