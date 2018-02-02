package dynamicdrillers.sagy;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_viewpager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));


        tabLayout = findViewById(R.id.main_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        button = findViewById(R.id.filter_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchVillagesActivity.class));
            }
        });
    }
}
///mayankkkkkkkk