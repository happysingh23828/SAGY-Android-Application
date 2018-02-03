package dynamicdrillers.sagy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    Button button;
    ImageView drawer_open_icon;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting Drawer Navigation By Happy Singh

        drawerLayout = findViewById(R.id.drawer_main);
        drawer_open_icon = findViewById(R.id.open_nav_icon);
        drawer_open_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.START);
            }
        });


        navigationView = findViewById(R.id.main_activity_nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Dashboard_item:
                        Toast.makeText(getBaseContext(),"Dashboard Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case  R.id.Notifications_item:
                        Toast.makeText(getBaseContext(),"Notification Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Complaints_item:
                        Toast.makeText(getBaseContext(),"Complaint Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Recent_ACtivity_item:
                        Toast.makeText(getBaseContext(),"Recent Activity  Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Videos:
                        Toast.makeText(getBaseContext(),"Videos Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case  R.id.about_app:
                        Toast.makeText(getBaseContext(),"About App Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case  R.id.about_sagy:
                        Toast.makeText(getBaseContext(),"About SAGY Clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.visit_website:
                        Toast.makeText(getBaseContext(),"Visite Website Clicked",Toast.LENGTH_SHORT).show();
                        break;


                }


                return false;

            }
        });

        // End Of Navigation Drawer






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