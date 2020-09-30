package com.example.monitoringsuhu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView suhu;
    DatabaseReference dref;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        suhu = (TextView) findViewById(R.id.sensor1);
        dref = FirebaseDatabase.getInstance().getReference().child("sensor1");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                status=dataSnapshot.child("suhu").getValue().toString();
                suhu.setText(status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
