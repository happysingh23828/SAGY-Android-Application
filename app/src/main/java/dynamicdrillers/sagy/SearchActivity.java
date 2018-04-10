package dynamicdrillers.sagy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchBtn,FilterBtn;
    RecyclerView recyclerView;
    String searchText;
    LinearLayout village,tahshil,state,mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        FilterBtn = findViewById(R.id.filter_btn);
        FilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),SearchVillagesActivity.class));
            }
        });

        searchEditText = findViewById(R.id.search_edittext);
        searchBtn = findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"constituency",0);
            }
        });

        village = findViewById(R.id.village);
        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"village",1);
            }
        });

        state = findViewById(R.id.state);
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"state",0);
            }
        });


        tahshil = (LinearLayout)findViewById(R.id.tahshil_layout);
        tahshil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"constituency",0);
            }
        });

        mp = findViewById(R.id.mp);
        mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"name",0);
            }
        });

        recyclerView = findViewById(R.id.search_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void search(String text,String order,int flag) {

        final int i = flag;
        Query query;
        if(flag==0)
            query = FirebaseDatabase.getInstance()
                .getReference().child("mp").orderByChild(order)
                .startAt(text).endAt(text+"\uf8ff");

        else
            query = FirebaseDatabase.getInstance()
                    .getReference().child("adopted_village").orderByChild(order)
                    .startAt(text).endAt(text+"\uf8ff");




        FirebaseRecyclerOptions<ModalSearchItem> options =
                new FirebaseRecyclerOptions.Builder<ModalSearchItem>()
                        .setQuery(query, ModalSearchItem.class)
                        .build();

        FirebaseRecyclerAdapter<ModalSearchItem,SearchViewHolder> adapter = new FirebaseRecyclerAdapter<ModalSearchItem,SearchViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull final SearchViewHolder holder, final int position, @NonNull final ModalSearchItem modal) {
                final int pos = position;



                if(i==1){

                    holder.setVillage(modal.getVillage());

                    FirebaseDatabase.getInstance().getReference().child("adopted_village")
                    .orderByChild("village").equalTo(modal.getVillage())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("mp").child(dataSnapshot1.child("adopted_by").getValue().toString())
                                                .addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        holder.setConstituency(dataSnapshot.child("constituency").getValue().toString());
                                                        holder.setImage(dataSnapshot.child("image").getValue().toString());
                                                        holder.setMp(dataSnapshot.child("name").getValue().toString());
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                }
                else {
                    holder.setConstituency(modal.getConstituency());
                    holder.setImage(modal.getImage());
                    holder.setMp(modal.getMp());
                }

                holder.getView().findViewById(R.id.mp_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==1){
                            FirebaseDatabase.getInstance().getReference().child("adopted_village")
                                    .orderByChild("village").equalTo(modal.getVillage()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                                        Intent intent = new Intent(getBaseContext(),MemberProfileActivity.class);
                                        intent.putExtra("mpid",dataSnapshot1.child("adopted_by").getValue().toString());
                                        startActivity(intent);

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        else{
                            Intent intent = new Intent(getBaseContext(),MemberProfileActivity.class);
                            intent.putExtra("mpid",getRef(position).getKey());
                            startActivity(intent);

                        }
                    }
                });

                final String id  = getRef(position).getKey();





            }

            @Override
            public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View mView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_search, parent, false);

                return new SearchViewHolder(mView);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        View mView;


        public SearchViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setImage(String image) {

            ImageView img =  mView.findViewById(R.id.search_image);;

            Picasso.with(SearchActivity.this).load(image).into(img);

        }

        public void setVillage(String village){

                TextView textView = mView.findViewById(R.id.village_text);
                textView.setText(village);
                textView.setVisibility(View.VISIBLE);


        }
        public void setConstituency(String tahshil){
            TextView textView = mView.findViewById(R.id.constituency_text);
            textView.setText(tahshil);
        }
        public void setMp(String mp){
            TextView textView = mView.findViewById(R.id.mp_text);
            textView.setText(mp);
        }


        public View getView(){
            return this.mView;
        }

    }

}
