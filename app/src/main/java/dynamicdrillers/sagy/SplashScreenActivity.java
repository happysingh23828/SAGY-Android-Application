package dynamicdrillers.sagy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    private final int SPLASH_DISPLAY_LENGTH = 10000;
    Button button;
    SharedPreferences sharedPreferences;
    Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        spinner =findViewById(R.id.lang_spinner);
        button = findViewById(R.id.splashbutton);
        sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime",true);

        //splash activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(firstTime) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            firstTime = false;
                            editor.putBoolean("firstTime", firstTime);
                            editor.apply();

                            Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                            finish();
                        }
                    }, SPLASH_DISPLAY_LENGTH);
                }
                else {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    SplashScreenActivity.this.startActivity(mainIntent);
                    finish();
                }

            }
        });



        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.language,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView mytext = (TextView) view;
        Toast.makeText(this,"You Selected"+mytext.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
