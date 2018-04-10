package dynamicdrillers.sagy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OneVillageWorkActivity extends AppCompatActivity {

    String title,desc,image;
    TextView Title,Desc;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_village_work);

        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("des");
        image = getIntent().getStringExtra("image");

        Title = findViewById(R.id.single_work_village_title);
        Desc = findViewById(R.id.single_work_village_description);
        imageView = findViewById(R.id.single_work_village_image);

        Title.setText(title);
        Desc.setText(desc);
        Picasso.with(this).load(image).into(imageView);

    }
}
