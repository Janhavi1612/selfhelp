package com.janhavi.android.self_help.ViewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.janhavi.android.self_help.FirebaseQueryLiveData;
import com.janhavi.android.self_help.Upload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel{

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");
    private FirebaseQueryLiveData liveData1 = new FirebaseQueryLiveData(databaseReference);

    public androidx.lifecycle.LiveData<DataSnapshot> getLiveData(){
        return liveData1;
    }
}
