package dynamicdrillers.sagy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class VillageInforamtionActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_inforamtion);

        toolbar = (Toolbar)findViewById(R.id.single_work_village_toolbar);
        getSupportActionBar().setTitle("Hagshag");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
