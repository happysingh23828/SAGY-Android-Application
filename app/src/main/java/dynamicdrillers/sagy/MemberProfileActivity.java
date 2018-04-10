package dynamicdrillers.sagy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberProfileActivity extends AppCompatActivity {

    CircleImageView image;
    TextView name,village,dob,residence,cons,state,party;
    String mp_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);
        image = findViewById(R.id.mp_info_image);
        name = findViewById(R.id.mp_info_name);
        village = findViewById(R.id.mp_info_adopted_village);
        dob = findViewById(R.id.mp_info_dob);
        residence = findViewById(R.id.mp_info_residence);
        cons = findViewById(R.id.mp_info_constituency);
        state = findViewById(R.id.mp_info_state);
        party = findViewById(R.id.mp_info_party);
        mp_id = getIntent().getStringExtra("mpid");

        FirebaseDatabase.getInstance().getReference().child("mp").child(mp_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                FirebaseDatabase.getInstance().getReference().child("adopted_village").child(dataSnapshot.child("villageadopted").getValue().toString())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot data) {

                                name.setText(dataSnapshot.child("name").getValue().toString().toUpperCase());
                                village.setText(data.child("village").getValue().toString().toUpperCase());
                                dob.setText(dataSnapshot.child("dob").getValue().toString().toUpperCase());
                                residence.setText(dataSnapshot.child("address").getValue().toString().toUpperCase());
                                cons.setText(dataSnapshot.child("constituency").getValue().toString().toUpperCase());
                                state.setText(dataSnapshot.child("state").getValue().toString().toUpperCase());
                                party.setText(dataSnapshot.child("party").getValue().toString().toUpperCase());
                                Picasso.with(getApplicationContext()).load(dataSnapshot.child("image").getValue().toString()).into(image);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
