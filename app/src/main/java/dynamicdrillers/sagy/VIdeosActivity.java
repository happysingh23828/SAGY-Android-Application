package dynamicdrillers.sagy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class VIdeosActivity extends AppCompatActivity {
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
            protected void onBindViewHolder(@NonNull ModelVideosViewHolder holder, int position, @NonNull ModelVideos model) {
                    holder.setTitle(model.getTitle());
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

    public static class ModelVideosViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public ModelVideosViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }

        public void setTitle(String title) {
            Button mbutton = mView.findViewById(R.id.videotitle);
            mbutton.setText(title);
        }

        public void setYoutubeid(String youtubeid) {


        }
    }
}
