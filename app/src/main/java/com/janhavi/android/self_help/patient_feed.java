package com.janhavi.android.self_help;

//import android.arch.lifecycle.ViewModelProvider;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.janhavi.android.self_help.ViewModels.PostViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelProviders.*;


public class patient_feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_feed);

        PostViewModel postViewModel= ViewModelProviders.of(this).get(PostViewModel.class);
        LiveData<DataSnapshot> liveData = postViewModel.getLiveData();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null){
                    //update UI here
                    String username;
                    String content;
                    String imageUri;
                }
            }
        });


    }
}
