package dynamicdrillers.sagy;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button button;
    private ImageView drawer_open_icon;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView searchtexview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchtexview = findViewById(R.id.search_textview);
        searchtexview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                  startActivity(intent);
            }
        });

        //Setting Drawer Navigation By Happy Singh

        drawerLayout = findViewById(R.id.drawer_main);
        drawer_open_icon = findViewById(R.id.open_nav_icon);
        drawer_open_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.START);
            }
        });


        navigationView = (NavigationView)findViewById(R.id.main_activity_nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment =null;
                switch (item.getItemId()) {
                    case R.id.Dashboard_item:
                        Toast.makeText(getBaseContext(), "DashBoard Clicked", Toast.LENGTH_SHORT).show();


                        break;

                    case R.id.Notifications_item:

                        Toast.makeText(getBaseContext(), "Notification Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Complaints_item:
//                        FragmentManager fragmentManager = getFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        ComplaintFragment complaintFragment =new ComplaintFragment();
//                        fragmentTransaction.replace(R.id.Complaint_fragment,complaintFragment);
//                        fragmentTransaction.commit();
                        break;

                    case R.id.Recent_ACtivity_item:
                        Toast.makeText(getBaseContext(), "Recent Activity  Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Videos:
                        drawerLayout.closeDrawer(Gravity.START);
                        Intent i = new Intent(MainActivity.this,VIdeosActivity.class);
                        startActivity(i);
                        break;

                    case R.id.about_app:
                        drawerLayout.closeDrawer(Gravity.START);
                        Intent aboutapp = new Intent(MainActivity.this,AboutAppActivity.class);
                        startActivity(aboutapp);
                        break;

                    case R.id.about_sagy:
                        drawerLayout.closeDrawer(Gravity.START);
                        Intent aboutsagy = new Intent(MainActivity.this,AboutSAGYActivity.class);
                        startActivity(aboutsagy);
                        break;

                    case R.id.visit_website:
                        Toast.makeText(getBaseContext(), "Visite Website Clicked", Toast.LENGTH_SHORT).show();
                        break;


                }

                return true;
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