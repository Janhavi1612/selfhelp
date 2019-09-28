package com.janhavi.android.self_help;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class doctor_add_post extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView postImage;
    private EditText postContent;
    private Button clickToAdd;
    private Button postButton;
    private ProgressBar progressBar;
    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_post);

        postImage = findViewById(R.id.postImage);
        postContent = findViewById(R.id.postContent);
        clickToAdd = findViewById(R.id.clickToAdd);
        postButton = findViewById(R.id.postButton);
        progressBar = findViewById(R.id.progressBar1);
       // progressBar.setProgress(0);
        storageReference= FirebaseStorage.getInstance().getReference("PostImages");
        databaseReference = FirebaseDatabase.getInstance().getReference("posts");

        clickToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPost();
            }
        });


    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(postImage);


        }
    }

    private String getFileExtension(Uri uri_post){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri_post));

    }

    private void uploadPost(){
        if(imageUri!= null){
            StorageReference postReference = storageReference.child(System.currentTimeMillis()+"."+
            getFileExtension(imageUri));
            postReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   // progressBar.setProgress(0);
                    Toast.makeText(doctor_add_post.this, R.string.success_message, Toast.LENGTH_SHORT).show();
                    Upload upload = new Upload(postContent.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                    String uploadID = databaseReference.push().getKey();
                    databaseReference.child(uploadID).setValue(upload);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(doctor_add_post.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    //double progress = 100.0 * (taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    //progressBar.setProgress((int)progress);

                }
            });
        }
    }
}
