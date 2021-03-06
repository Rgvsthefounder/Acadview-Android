package com.assembled.ChatGroups;

import android.support.annotation.NonNull;
//import android.support.v7.app.AlertController;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assembled.firebase.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // request code for signin
    private static final int RC_SIGN_IN = 123;
    private static final int RC_PIX = 101;

    ArrayList<MessageModel> messageData;

    EditText editText;
    Button button;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference messageReference, userReference;

    //  Authenticator providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build());


    //  Authenticator

    private FirebaseAuth firebaseAuth;

    //    user
    private FirebaseUser user;


    //    auth state listener
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.button);
        messageAdapter = new MessageAdapter(this);
        recyclerView.setAdapter(messageAdapter);

        messageData = new ArrayList<>();
        //1
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        userReference = firebaseDatabase.getReference("users");
        messageReference = firebaseDatabase.getReference("message");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userMessage = editText.getText().toString().trim();

                if (userMessage.length() == 0) {
                    return;
                }

                String name = user.getDisplayName();
                String email = user.getEmail();
                String userImg = user.getPhotoUrl().toString();
                String time = String.valueOf(System.currentTimeMillis() / 1000);

                MessageModel message = new MessageModel(name, email, userImg, userMessage, time);

                messageReference.push().setValue(message);
                editText.setText(" ");
            }

        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

                if (user != null) {


                    // get user detail
                    String id = user.getUid();
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    String imgURL = user.getPhotoUrl().toString();


                    //create model
                    UserModel userModel = new UserModel(id, name, email, imgURL);

                    //add to firebase database
                    userReference.child(userModel.getId()).setValue(userModel);


                } else {

                    // Create and launch sign-in intent

                    StartSignIn();
                }
            }
        };
        messageReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                MessageModel message = dataSnapshot.getValue(MessageModel.class);

                messageData.add(message);
                messageAdapter.swap(messageData);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

      firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void StartSignIn() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

        }

        //menu for sign out


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.signout:
                StartSignOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void StartSignOut() {

        AuthUI.getInstance()
                .signOut(MainActivity.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(MainActivity.this,"Succesfully Signed Out",Toast.LENGTH_SHORT).show();

                        // ...
                    }
                });

    }

}

