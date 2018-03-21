package dynamicdrillers.sagy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    CustomSwipeAdapter customSwipeAdapter;
    ViewPager viewPager;
    RecyclerView rv=null;
    LinearLayout sliderpanel;
    private int dotscount;
    private ImageView[] dots;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        sliderpanel =view.findViewById(R.id.sliderdots);

        customSwipeAdapter = new CustomSwipeAdapter(this.getActivity());
        viewPager.setAdapter(customSwipeAdapter);
        dotscount = customSwipeAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i=0;i<dotscount;i++){
            dots[i]= new ImageView(this.getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderpanel.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<dotscount;i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.nonactive_dot));

                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                DashboardFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(viewPager.getCurrentItem()==0){
                            viewPager.setCurrentItem(1);
                        }
                        else if (viewPager.getCurrentItem()==1){
                            viewPager.setCurrentItem(2);
                        }
                        else if(viewPager.getCurrentItem()==2) {
                            viewPager.setCurrentItem(3);
                        }
                        else if (viewPager.getCurrentItem()==3){
                            viewPager.setCurrentItem(4);
                        }
                        else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 200, 4000);

        rv = (RecyclerView) view.findViewById(R.id.rv_recycler_view);
//        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        rv.setLayoutManager(linearLayoutManager);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("adopted_village");
        mAuth = FirebaseAuth.getInstance();

        //new recycler view


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Query query = mDatabase;

        FirebaseRecyclerOptions<ModelVillage> options = new FirebaseRecyclerOptions.Builder<ModelVillage>()
                .setQuery(query,ModelVillage.class)
                .build();
        FirebaseRecyclerAdapter<ModelVillage,ModelVillageViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelVillage, ModelVillageViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ModelVillageViewHolder holder, int position, @NonNull ModelVillage model) {
                            holder.setVillage(model.getVillage());
                            holder.setThasil("Block :"+model.getThasil());
                            holder.setDistrict("District :"+model.getDistrict());
                            holder.setState(model.getState());

                            final String villageid = getRef(position).getKey().toString();
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getContext(), villageid, Toast.LENGTH_SHORT).show();
                                }
                            });

                    }

                    @Override
                    public ModelVillageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        View view =  LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.village_item, parent, false);
                        return  new ModelVillageViewHolder(view);
                    }
                };
        rv.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }
    public  static class ModelVillageViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public ModelVillageViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }
        public void setVillage(String village) {
            TextView mvillage = mView.findViewById(R.id.village_name);
            mvillage.setText(village);

        }
        public void setThasil(String thasil) {
            TextView mthasil = mView.findViewById(R.id.block_name);
            mthasil.setText(thasil);

        }


        public void setState(String state) {
            TextView mstate = mView.findViewById(R.id.state_name);
            mstate.setText(state);
        }
        public void setDistrict(String district) {
            TextView mdistrict = mView.findViewById(R.id.district_name);
            mdistrict.setText(district);

        }
    }
}
