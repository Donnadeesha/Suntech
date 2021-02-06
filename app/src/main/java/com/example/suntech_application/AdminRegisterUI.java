package com.example.suntech_application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suntech_application.Admin.Admin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminRegisterUI extends AppCompatActivity {

    //DECLARING FIELDS
    EditText sid,name,username,password;

    Button btnAdminReg;

    DatabaseReference dbRef;
    Admin Admin;

    public static String key;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register_ui);



        //INITIALIZING FIELDS
        sid = (EditText)findViewById(R.id.etAdminID);
        name = (EditText)findViewById(R.id.etAdminName);
        username = (EditText)findViewById(R.id.etAdminUserName);
        password = (EditText) findViewById(R.id.etAdminPassword);


        btnAdminReg = (Button)findViewById(R.id.btnRegisterAdmin);

        //NEW Admin OBJECT
        Admin = new Admin();

        dbRef = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Admin");

        //Validating ALL THE USER INPUT FIELDS NOT TO BE NULL
        btnAdminReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(sid.getText().toString())
                        || TextUtils.isEmpty(name.getText().toString().trim())
                        || TextUtils.isEmpty(username.getText().toString())
                        || TextUtils.isEmpty(password.getText().toString())

                )
                { Toast.makeText(getApplicationContext(),"Please fill all the fields !",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        Admin.setSid(sid.getText().toString().trim());
                        Admin.setName(name.getText().toString().trim());
                        Admin.setUserName(username.getText().toString().trim());
                        Admin.setPassword(password.getText().toString().trim());


                        //INSERT INTO DATABASE - NORMAL METHOD
                        //dbRef.push().setValue(Nurse);

                        key = username.getText().toString().trim();
                        //INSERT INTO DATABASE - WITH AUTO INCREMENT
                        dbRef.child(key).setValue(Admin);
                        //customToastShow();
                        Toast.makeText(getApplicationContext(),"SUCCESS !",Toast.LENGTH_SHORT).show();
                        goBackLogin();

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"ERROR !",Toast.LENGTH_SHORT).show();
                    }

                }


              }
          }
        );
    }



    private void goBackLogin() {
        Intent intent = new Intent(AdminRegisterUI.this,MainActivity.class);
        startActivity(intent);
    }
}