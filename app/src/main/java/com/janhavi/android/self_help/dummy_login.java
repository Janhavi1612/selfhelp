package com.janhavi.android.self_help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dummy_login extends AppCompatActivity {

    private Button doctorlogin;
    private Button patientlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_login);

        doctorlogin = findViewById(R.id.doctorlogin);
        patientlogin = findViewById(R.id.patientlogin);
        doctorlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doctoraddpost = new Intent(dummy_login.this, doctor_add_post.class);
                startActivity(doctoraddpost);
            }
        });

        patientlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent patientfeed = new Intent(dummy_login.this, patient_feed.class);
                startActivity(patientfeed);
            }
        });
    }





}
