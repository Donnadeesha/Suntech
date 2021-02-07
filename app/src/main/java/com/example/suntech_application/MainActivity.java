package com.example.suntech_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.suntech_application.Admin.Admin;
import com.example.suntech_application.Laptop.Laptop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Spinner typeSpinner;
    public static String userTypeValue;

    EditText username,password;

    private DatabaseReference ref;

    String uName,pWord;

    // variable to pass the value
    public static final String EXTRA_USERNAME = "username pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        typeSpinner = (Spinner)findViewById(R.id.spUserType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.userTypes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        typeSpinner.setOnItemSelectedListener(this);


        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        Button btnRegNow = (Button)findViewById(R.id.btnRegister);

        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || userTypeValue.equals("NULL")) {
                    if (TextUtils.isEmpty(username.getText().toString())) {

                        Toast.makeText(getApplicationContext(),"Enter a username",Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(password.getText().toString())) {

                        Toast.makeText(getApplicationContext(),"Enter a password",Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(),"Select a user type.",Toast.LENGTH_SHORT).show();
                    }
                } else {

                    if (userTypeValue.equals("Admin")) {

                        ref = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Admin");

                        uName = username.getText().toString();
                        pWord = password.getText().toString();


                        ref.child(uName).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                try {
                                    Admin Admin = dataSnapshot.getValue(Admin.class);
                                    if (pWord.equals(Admin.getPassword())) {

                                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                                        openAdminMainUI();
                                    } else {

                                        Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NullPointerException e) {

                                    Toast.makeText(getApplicationContext(),"Admin Record Not Found.",Toast.LENGTH_SHORT).show();
                                    clearControls();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                    else if (userTypeValue.equals("Laptop")) {

                        ref = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/"   ).child("Laptop");

                        uName = username.getText().toString();
                        pWord = password.getText().toString();


                        ref.child(uName).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                try {
                                    Laptop doctor = dataSnapshot.getValue(Laptop.class);
                                    if (pWord.equals(doctor.getPassword())) {

                                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                                        openLaptopProfileUI();

                                    } else {
                                        Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NullPointerException e) {

                                    Toast.makeText(getApplicationContext(),"Laptop Record Not Found.",Toast.LENGTH_SHORT).show();

                                    clearControls();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }


                }


            }
        });



        btnRegNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterMainUI();
            }
        });


    }



    private void clearControls() {
        username.setText("");
        password.setText("");
        userTypeValue.equals("NULL");
    }



    private void openAdminMainUI() {
        Intent intent = new Intent(MainActivity.this,AdminMainUI.class);
        startActivity(intent);
    }

    private void openLaptopProfileUI() {
        Intent intent = new Intent(MainActivity.this,LaptopProfileUI.class);
        intent.putExtra(EXTRA_USERNAME,uName);
        startActivity(intent);
    }


    private void openRegisterMainUI() {
        Intent intent = new Intent(MainActivity.this,RegisterMainUI.class);
        startActivity(intent);
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (parent.getItemAtPosition(position).toString().equals("Select User")) {
            //Do Nothing
            userTypeValue = "NULL";
        }
        else if (parent.getItemAtPosition(position).toString().equals("Admin")) {
            String text = parent.getItemAtPosition(position).toString();
            userTypeValue = text;
            Toast.makeText(parent.getContext(),"Selected User : " + text,Toast.LENGTH_SHORT).show();
        }
        else if (parent.getItemAtPosition(position).toString().equals("Laptop")) {
            String text = parent.getItemAtPosition(position).toString();
            userTypeValue = text;
            Toast.makeText(parent.getContext(),"Selected User : " + text,Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





}
