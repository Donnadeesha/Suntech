package com.example.suntech_application;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suntech_application.Laptop.Laptop;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LaptopProfileUI extends AppCompatActivity {


    DatabaseReference ref;

    EditText id,model,quantity,username,password;

    Laptop lap;

    String uName;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.laptop_profile_ui);

        //value passed from main activity to this class
        final Intent intent = getIntent();
        uName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);

        lap = new Laptop();

        id = (EditText)findViewById(R.id.etLaptopID);
        model = (EditText)findViewById(R.id.etLaptopModel);
        quantity = (EditText)findViewById(R.id.etLaptopAmount);
        username = (EditText)findViewById(R.id.etLaptopUsername);
        password = (EditText)findViewById(R.id.etLaptopPassword);

        Button btnUpdateLap = (Button)findViewById(R.id.btnUpdateLaptop);
        Button btnDeleteLap = (Button)findViewById(R.id.btnDeleteLaptop);

        ref = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Laptop");


        ref.child(uName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    Laptop laptop = dataSnapshot.getValue(Laptop.class);
                    id.setText(laptop.getId());
                    model.setText(laptop.getModel());
                    quantity.setText(laptop.getQuantity());
                    username.setText(laptop.getUsername());
                    password.setText(laptop.getPassword());




                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //UPDATE DETAILS OF LAPTOP
        btnUpdateLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Laptop");

                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {

                            if(TextUtils.isEmpty(id.getText().toString()) || TextUtils.isEmpty(model.getText().toString())
                                    || TextUtils.isEmpty(quantity.getText().toString()) || TextUtils.isEmpty(password.getText().toString())
                                    ) {

                                Toast.makeText(getApplicationContext(),"Fields cannot be empty.",Toast.LENGTH_SHORT).show();

                            }
                            else {
                             //   lap.setUserName(uName);
                             //   lap.setPassword(password);

                                lap.setId(id.getText().toString().trim());
                                lap.setModel(model.getText().toString().trim());
                                lap.setQuantity(quantity.getText().toString().trim());
                                lap.setUsername(username.getText().toString().trim());
                                lap.setPassword(password.getText().toString().trim());




                                ref = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Laptop").child(uName);
                                ref.setValue(lap);


                                Toast.makeText(getApplicationContext(),"Update Successful..",Toast.LENGTH_SHORT).show();

                                finish();
                            }

                        }
                        catch (Exception e) {

                            Toast.makeText(getApplicationContext(),"Failed to update...",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //DELETE RECORD
        btnDeleteLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Laptop");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild(uName)) {

                            ref = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Laptop").child(uName);
                            ref.removeValue();

                            Toast.makeText(getApplicationContext()," Record Deleted Successfully.",Toast.LENGTH_SHORT).show();
                            goBackLogin();
                        }
                        else {

                            Toast.makeText(getApplicationContext(),"Error Deleting Record.",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });




    }

    private void goBackLogin() {
        Intent intent = new Intent(LaptopProfileUI.this,MainActivity.class);
        startActivity(intent);
    }



}
