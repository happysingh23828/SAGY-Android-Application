package dynamicdrillers.sagy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class VillageInforamtionActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tmpname,tparty,tstate,tconstituency;
    String Village_id;
    CircleImageView image;
    RecyclerView   recyclerView;
    Button gotomp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_inforamtion);
        tmpname = findViewById(R.id.village_info_mp_name);
        tconstituency = findViewById(R.id.village_info_constituency);
        tparty = findViewById(R.id.village_info_mp_party);
        tstate = findViewById(R.id.village_info_state);
        image = findViewById(R.id.village_info_mp_image);
        gotomp = findViewById(R.id.bt_village_info_more_mp_more);

        gotomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MemberProfileActivity.class);
                intent.putExtra("mpid",getIntent().getStringExtra("mp_id"));
                startActivity(intent);

            }
        });


        Intent intent = getIntent();
        String mp_id = intent.getStringExtra("mp_id");
        Village_id = getIntent().getStringExtra("village_id");

        Toast.makeText(this, Village_id, Toast.LENGTH_SHORT).show();


        recyclerView = findViewById(R.id.village_info_work_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseDatabase.getInstance().getReference().child("mp").child(mp_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String mp_name = dataSnapshot.child("name").getValue().toString();
               tmpname.setText(mp_name.toUpperCase());
                String constituency = dataSnapshot.child("constituency").getValue().toString();
                tconstituency.setText(constituency);
                String party = dataSnapshot.child("party").getValue().toString();
                tparty.setText(party.toUpperCase());
                String state = dataSnapshot.child("state").getValue().toString();
                tstate.setText(state.toUpperCase());
                String mp_image = dataSnapshot.child("image").getValue().toString();


                Picasso.with(getApplicationContext()).load(mp_image).into(image);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        Query query = FirebaseDatabase.getInstance().getReference().child("adopted_village").child(Village_id).child("work");

        FirebaseRecyclerOptions<ModelWork> options = new FirebaseRecyclerOptions.Builder<ModelWork>()
                .setQuery(query,ModelWork.class)
                .build();

        FirebaseRecyclerAdapter<ModelWork,VillageViewPageHolder> adaptor = new FirebaseRecyclerAdapter<ModelWork, VillageViewPageHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull VillageViewPageHolder holder, int position, @NonNull final ModelWork model) {

                holder.setTitle(model.getTitle());
                holder.setDescription(model.getDescription());

                holder.mView.findViewById(R.id.bt_village_info_more_work).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(VillageInforamtionActivity.this,OneVillageWorkActivity.class);
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("des",model.getDescription());
                        intent.putExtra("image",model.getImage());
                        startActivity(intent);
                    }
                });




            }

            @Override
            public VillageViewPageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view =  LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_village_work, parent, false);
                return  new VillageViewPageHolder(view);

            }
        };

        recyclerView.setAdapter(adaptor);
        adaptor.startListening();
    }

    public  class  VillageViewPageHolder extends RecyclerView.ViewHolder{

        View mView;
        public VillageViewPageHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }

        public void setDescription(String description) {
            TextView textView = mView.findViewById(R.id.village_info_work_title);
            textView.setText(description);
        }
        public void setTitle(String title) {
            TextView textView = mView.findViewById(R.id.village_info_work_desc);
            textView.setText(title);
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
