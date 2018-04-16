package dynamicdrillers.sagy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchVillagesActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Spinner stateSpinner,districtSpinner;
    private String state[] = {"Bihar","Jharkhand"};
    private String selectedstate="Jharkhand";
    private FirebaseListAdapter<ConstituencyModal> adapter;
    private String id;
    private TextView village,tahshil_name,mp_name;
    private Button more_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_villages);


        toolbar = findViewById(R.id.simple_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Villages");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        village = findViewById(R.id.village_name);
        more_info = findViewById(R.id.more_info);
        mp_name = findViewById(R.id.mp_name);


        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MemberProfileActivity.class);
                intent.putExtra("mpid",id);
                startActivity(intent);
            }
        });


        districtSpinner = findViewById(R.id.district_spinner);
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                TextView textView = (TextView) parent.findViewById(R.id.text_item);

                FirebaseDatabase.getInstance()
                        .getReference().child("mp").orderByChild("constituency").equalTo(textView.getText().toString())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                                    id = dataSnapshot1.getRef().getKey().toString();
                                    FirebaseDatabase.getInstance().getReference().child("mp")
                                            .child(dataSnapshot1.getRef().getKey().toString())
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot2) {

                                                    mp_name.setText(dataSnapshot2.child("name").getValue().toString());
                                                    FirebaseDatabase.getInstance()
                                                            .getReference().child("adopted_village")
                                                            .child(dataSnapshot2.child("villageadopted").getValue().toString())
                                                            .addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    village.setText(dataSnapshot.child("village").getValue().toString());
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

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                id = textView.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,R.layout.simple,R.id.text_item,state);
        stateSpinner = findViewById(R.id.state_spinner);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                selectedstate = (String) ((TextView)view.findViewById(R.id.text_item)).getText();


                Query query = FirebaseDatabase.getInstance()
                        .getReference().child("mp").orderByChild("state").equalTo(selectedstate);

                FirebaseListOptions<ConstituencyModal> options = new FirebaseListOptions.Builder<ConstituencyModal>()
                        .setLayout(R.layout.simple)//Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                        .setQuery(query, ConstituencyModal.class)
                        .build();

                adapter = new FirebaseListAdapter<ConstituencyModal>(options) {
                    @Override
                    protected void populateView(View v,ConstituencyModal model, int position) {
                        TextView textView =  (TextView) v.findViewById(R.id.text_item);
                        textView.setText(model.getConstituency().toString());
                    }
                };


                districtSpinner.setAdapter(adapter);
                adapter.startListening();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
