package dynamicdrillers.sagy;

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
    private String state[] = {"mp","up"};
    private String selectedstate="mp";
    private List<String> messageList = new ArrayList<>();
    private ListView listView;
    private FirebaseListAdapter<VillageModal> adapter;
    private String id;
    private TextView village,tahshil_name,mp_name;
    private Button check_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_villages);


        toolbar = findViewById(R.id.simple_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Villages");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        village = findViewById(R.id.village_name);
        tahshil_name = findViewById(R.id.tahshil_name);
        mp_name = findViewById(R.id.mp_name);
        check_btn = findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              FirebaseDatabase.getInstance()
                        .getReference().child("state").child(selectedstate).child(id).addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                VillageModal modal = dataSnapshot.getValue(VillageModal.class);
                                tahshil_name.setText(modal.getTahshil());
                                mp_name.setText(modal.getMp());
                                village.setText(modal.getVillage());
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                ;
            }
        });

        districtSpinner = findViewById(R.id.district_spinner);
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                DatabaseReference itemRef = adapter.getRef(i);
                id = itemRef.getKey().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                        .getReference().child("state").child(selectedstate);

                FirebaseListOptions<VillageModal> options = new FirebaseListOptions.Builder<VillageModal>()
                        .setLayout(R.layout.simple)//Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                        .setQuery(query, VillageModal.class)
                        .build();

                adapter = new FirebaseListAdapter<VillageModal>(options) {
                    @Override
                    protected void populateView(View v,VillageModal model, int position) {
                        TextView textView =  (TextView) v.findViewById(R.id.text_item);
                        textView.setText(model.getVillage());
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
