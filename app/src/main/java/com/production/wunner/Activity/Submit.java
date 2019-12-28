package com.production.wunner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.production.wunner.Adapter.Tab_Layout_Adapter;
import com.production.wunner.Fragment.fragment_rated;
import com.production.wunner.Fragment.fragment_record;
import com.production.wunner.Fragment.fragment_sumary;
import com.production.wunner.R;

import java.util.ArrayList;
import java.util.List;

public class Submit extends AppCompatActivity {

    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private String team_id;
    ViewPager mViewPager;
    String station_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Intent intent=new Intent();
        station_id=intent.getStringExtra("station_id");
        if(database!=null) {
            reference = database.getReference(station_id);
            reference.setValue("Updated");
            UpdateSumary();
        }
        mViewPager =findViewById(R.id.vpPager);

        Tab_Layout_Adapter adapter = new Tab_Layout_Adapter(getSupportFragmentManager());
        adapter.addFragment(fragment_sumary.newInstance(this), "Summary");
        adapter.addFragment(fragment_record.newInstance(this), "Record");
        mViewPager.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valume =dataSnapshot.getValue(String.class);
                if (valume!="Updated" && valume!= "Rated")
                {
                    //todo team id nè 
                    team_id=valume;
                    SubmitScore();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"Failed to read value.",Toast.LENGTH_SHORT);
            }
        });
        Button buttonPost=findViewById(R.id.button_Post);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sau khi xử lý xong.

                reference.setValue("Rated");
            }
        });
    }

    private void UpdateSumary() {
        ArrayList<String> data= new ArrayList();
        data.add("team1");
        data.add("station1");
        data.add("1800");
        fragment_sumary.newInstance(getApplication()).UpdateData(data);
    }

    private void SubmitScore() {
        Toast.makeText(this.getBaseContext(),"Updated",Toast.LENGTH_SHORT).show();
        fragment_record.newInstance(getApplicationContext()).submit(station_id,team_id);
        //get về xong push lên
    }
}
