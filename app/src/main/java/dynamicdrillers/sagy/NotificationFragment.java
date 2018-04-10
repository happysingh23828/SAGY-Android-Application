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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    RecyclerView rv = null;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        rv = (RecyclerView) view.findViewById(R.id.notification_recycler_view);
//        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(linearLayoutManager);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("notification");
        mAuth = FirebaseAuth.getInstance();

        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Query query = mDatabase;

        FirebaseRecyclerOptions<ModelNotification> options = new FirebaseRecyclerOptions.Builder<ModelNotification>()
                .setQuery(query,ModelNotification.class)
                .build();
        FirebaseRecyclerAdapter<ModelNotification,NotificationViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelNotification, NotificationViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final NotificationViewHolder holder, final int position, @NonNull ModelNotification model) {

                        holder.setTitle(model.getTitle());
                        holder.setDescription(model.getDescription());
                        holder.itemView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {



                            }
                        });

                    }

                    @Override
                    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        View view =  LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.notification_item, parent, false);
                        return  new NotificationViewHolder(view);
                    }
                };
        rv.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public  static class NotificationViewHolder  extends RecyclerView.ViewHolder{
        View mView;


        public NotificationViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }
        public void setTitle(String title) {
            TextView mtitle = mView.findViewById(R.id.notification_title);
            mtitle.setText(title);
        }

        public void setDescription(String description) {
            TextView mdesc = mView.findViewById(R.id.notification_desc);
            mdesc.setText(description);
        }


    }

}
