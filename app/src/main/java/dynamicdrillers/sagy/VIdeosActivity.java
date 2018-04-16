package dynamicdrillers.sagy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.*;
import com.google.android.youtube.player.YouTubePlayerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class VIdeosActivity extends YouTubeBaseActivity{
    RecyclerView videorv = null;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        videorv = findViewById(R.id.videosRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        videorv.setLayoutManager(linearLayoutManager);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("videos");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query query = mDatabase;

        FirebaseRecyclerOptions<ModelVideos> options = new FirebaseRecyclerOptions.Builder<ModelVideos>()
                .setQuery(query,ModelVideos.class)
                .build();
        FirebaseRecyclerAdapter<ModelVideos,ModelVideosViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelVideos, ModelVideosViewHolder>
                (options) {
            @Override
            protected void onBindViewHolder(@NonNull ModelVideosViewHolder holder, int position, @NonNull final ModelVideos model) {
                    holder.setTitle(model.getTitle());
                    holder.setYoutubeid(model.getYoutubeid());
                //Toast.makeText(VIdeosActivity.this, model.getTitle(), Toast.LENGTH_SHORT).show();

                 holder.mView.findViewById(R.id.img_youtube).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent = new Intent(getApplication(),SingleVideoActivity.class);
                         intent.putExtra("id",model.getYoutubeid());
                         startActivity(intent);
                     }
                 });
            }

            @Override
            public ModelVideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view =  LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.videos_item, parent, false);
                return new ModelVideosViewHolder(view);
            }
        };
        videorv.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }

    public  class ModelVideosViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public ModelVideosViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }

        public void setTitle(String title) {
            Button mbutton = mView.findViewById(R.id.videotitle);
            mbutton.setText(title);
        }



        public void setYoutubeid(final String youtube) {

           // Toast.makeText(VIdeosActivity.this, youtube, Toast.LENGTH_SHORT).show();

        ImageView img = mView.findViewById(R.id.img_youtube);
            Picasso.with(VIdeosActivity.this).load("https://i.ytimg.com/vi/"+youtube+"/maxresdefault.jpg").into(img);

        }
    }


}
