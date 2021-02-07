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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suntech_application.Laptop.Laptop;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LaptopRegisterUI extends AppCompatActivity {


    //DECLARING FIELDS
    EditText id,model,quantity,username,password;

    Button btnLaptopReg;

    DatabaseReference dbRef;
    Laptop Laptop;

    public static String key;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.laptop_register_ui);



        //INITIALIZING FIELDS
        id = (EditText)findViewById(R.id.etLaptopID);
        model = (EditText)findViewById(R.id.etLaptopModel);
        quantity = (EditText)findViewById(R.id.etLaptopAmount);
        username = (EditText)findViewById(R.id.etLaptopUsername);
        password = (EditText) findViewById(R.id.etLaptopPassword);



        btnLaptopReg = (Button)findViewById(R.id.btnRegisterLaptop);

       //New Laptop Object
        Laptop = new Laptop();

        dbRef = FirebaseDatabase.getInstance().getReference("https://suntechapplication-default-rtdbfirebaseiocom/").child("Laptop");

        //Validating ALL THE USER INPUT FIELDS NOT TO BE NULL
        btnLaptopReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(id.getText().toString())
                        || TextUtils.isEmpty(model.getText().toString().trim())
                        || TextUtils.isEmpty(quantity.getText().toString())
                        || TextUtils.isEmpty(username.getText().toString())
                        || TextUtils.isEmpty(password.getText().toString())

                )
                { Toast.makeText(getApplicationContext(),"Please fill all the fields !",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        Laptop.setId(id.getText().toString().trim());
                        Laptop.setModel(model.getText().toString().trim());
                        Laptop.setQuantity(quantity.getText().toString().trim());
                        Laptop.setUsername(username.getText().toString().trim());
                        Laptop.setPassword(password.getText().toString().trim());


                        //INSERT INTO DATABASE - NORMAL METHOD
                        //dbRef.push().setValue(Nurse);

                        key = username.getText().toString().trim();
                        //INSERT INTO DATABASE - WITH AUTO INCREMENT
                        dbRef.child(key).setValue(Laptop);
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
        Intent intent = new Intent(LaptopRegisterUI.this,MainActivity.class);
        startActivity(intent);
    }

}
