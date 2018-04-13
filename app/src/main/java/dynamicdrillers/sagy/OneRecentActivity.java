package dynamicdrillers.sagy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OneRecentActivity extends AppCompatActivity {
    String activityid;
    TextView textViewtitle,textViewdesc;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_recent);
        textViewtitle = findViewById(R.id.recent_title);
        textViewdesc = findViewById(R.id.recent_desc);
        imageView = findViewById(R.id.recent_image);
        activityid = getIntent().getStringExtra("activity");
        FirebaseDatabase.getInstance().getReference().child("activities").child(activityid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewtitle.setText(dataSnapshot.child("title").getValue().toString().toUpperCase());
                textViewdesc.setText(dataSnapshot.child("description").getValue().toString());
                Picasso.with(getApplicationContext()).load(dataSnapshot.child("image").getValue().toString()).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
