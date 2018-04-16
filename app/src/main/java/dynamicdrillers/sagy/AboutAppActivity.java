package dynamicdrillers.sagy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.sony.fancyaboutpagelib.FancyAboutPage;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        FancyAboutPage fancyAboutPage=findViewById(R.id.aboutpage);
        //fancyAboutPage.setCoverTintColor(Color.BLUE);  //Optional
        fancyAboutPage.setCover(R.drawable.sliderb); //Pass your cover image
        fancyAboutPage.setName("Dynamic Driller");
        fancyAboutPage.setDescription("We Are A Team Of Android Developer | Android App, Web and Software Developer.");
        fancyAboutPage.setAppIcon(R.drawable.icon_about); //Pass your app icon image
        fancyAboutPage.setAppName("SAGY APP");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.2.3");
        fancyAboutPage.setAppDescription("On India’s Independence Day, the Prime Minister Shri Narendra Modi, made a commitment to launch the Saansad Adarsh Gram Yojana (SAANJHI). Holding true the commitment made, he is launching the Scheme on 11th October, 2014 - Lok Nayak Jai Prakash Narayan Ji’s birth anniversary – at Vigyan Bhawan, New Delhi.\n" +
                "\n" +
                "\n" +
                "The goal is to develop three Adarsh Grams by March 2019, of which one would be achieved by 2016. Thereafter, five such Adarsh Grams (one per year) will be selected and developed by 2024. \n" +
                "\n" +
                "Inspired by the principles and values of Mahatma Gandhi, the Scheme places equal stress on nurturing values of national pride, patriotism, community spirit, self-confidence and on developing infrastructure. The SAANJHI will keep the soul of rural India alive while providing its people with quality access to basic amenities and opportunities to enable them to shape their own destiny");
        fancyAboutPage.addEmailLink("dynamicdriller5@gmail.com");     //Add your email id
        fancyAboutPage.addFacebookLink("https://www.facebook.com/dynamicdriller5");   //Add your facebook address url
        fancyAboutPage.addTwitterLink("https://twitter.com/dynamicdriller5");
        fancyAboutPage.addLinkedinLink("https://www.linkedin.com/in/dynamic-driller5-a9989b5/");
        fancyAboutPage.addGitHubLink("https://github.com/Krishnasony");
    }
}
