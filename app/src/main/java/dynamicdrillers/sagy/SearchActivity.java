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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchBtn;
    RecyclerView recyclerView;
    String searchText;
    LinearLayout village,tahshil,state,mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.search_edittext);
        searchBtn = findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"village");
            }
        });

        village = findViewById(R.id.village);
        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"village");
            }
        });

        state = findViewById(R.id.state);
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"state");
            }
        });


        tahshil = (LinearLayout)findViewById(R.id.tahshil_layout);
        tahshil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"tahshil");
            }
        });

        mp = findViewById(R.id.mp);
        mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                search(searchText,"mp");
            }
        });

        recyclerView = findViewById(R.id.search_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void search(String text,String order) {


        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Adobted_villages").orderByChild(order).startAt(text).endAt(text+"\uf8ff");


        FirebaseRecyclerOptions<ModalSearchItem> options =
                new FirebaseRecyclerOptions.Builder<ModalSearchItem>()
                        .setQuery(query, ModalSearchItem.class)
                        .build();

        FirebaseRecyclerAdapter<ModalSearchItem,SearchViewHolder> adapter = new FirebaseRecyclerAdapter<ModalSearchItem,SearchViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull final SearchViewHolder holder, int position, @NonNull final ModalSearchItem modal) {
                final int pos = position;

                holder.setVillage(modal.getVillage());
                holder.setTahshil(modal.getTahshil());
                holder.setImage(modal.getImage());
                holder.setMp(modal.getMp());
                final String id  = getRef(position).getKey();

                holder.getView().findViewById(R.id.mp_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SearchActivity.this,id,Toast.LENGTH_LONG).show();

                    }
                });



            }

            @Override
            public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View mView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_search, parent, false);

                return new SearchViewHolder(mView);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        View mView;


        public SearchViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setImage(String image) {

            ImageView img =  mView.findViewById(R.id.search_image);;

            Picasso.with(SearchActivity.this).load(image).into(img);

        }

        public void setVillage(String village){
            TextView textView = mView.findViewById(R.id.village_text);
            textView.setText(village);
        }
        public void setTahshil(String tahshil){
            TextView textView = mView.findViewById(R.id.tahshil_text);
            textView.setText(tahshil);
        }
        public void setMp(String mp){
            TextView textView = mView.findViewById(R.id.mp_text);
            textView.setText(mp);
        }


        public View getView(){
            return this.mView;
        }

    }

}
