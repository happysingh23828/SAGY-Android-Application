package dynamicdrillers.sagy;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends Fragment {
    EditText editTextcomplaint;
    AppCompatEditText editTextname,editTextemail,editTextpass;
    DatabaseReference mDatabase;




    public ComplaintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);

        Button button = view.findViewById(R.id.send);
        editTextemail = view.findViewById(R.id.edittextemail);
        editTextname  = view.findViewById(R.id.edittextname);
        editTextpass  = view.findViewById(R.id.edittextpass);
        editTextcomplaint = view.findViewById(R.id.edittextcomplaint);
        mDatabase     = FirebaseDatabase.getInstance().getReference().child("complaints").push();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextname.getText().toString().trim();
                String email = editTextemail.getText().toString().trim();
                String phone = editTextpass.getText().toString().trim();
                String complaint = editTextcomplaint.getText().toString().trim();
                mDatabase.child("name").setValue(name);
                mDatabase.child("email").setValue(email);
                mDatabase.child("phone").setValue(phone);
                mDatabase.child("complaint").setValue(complaint);
                Toast.makeText(getContext(),"your Complaint is sended successfully",Toast.LENGTH_LONG).show();
                Snackbar.make(view,"your Complaint is sended successfully",Snackbar.LENGTH_LONG).show();
                editTextcomplaint.setText("");
                editTextemail.setText("");
                editTextname.setText("");
                editTextpass.setText("");

            }
        });
        return view;
    }

}
