package com.example.suntech_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.suntech_application.Laptop.Laptop;

public class RegisterMainUI  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main_ui);

        Button btnadmin = (Button)findViewById(R.id.btnAdmin);
        Button btnlaptop = (Button)findViewById(R.id.btnLaptop);


        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminRegisterUI();
            }
        });

        btnlaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLaptopRegisterUI();
            }
        });

    }

    private void openAdminRegisterUI() {
        Intent intent = new Intent(RegisterMainUI.this,AdminRegisterUI.class);
        startActivity(intent);
    }

    private void openLaptopRegisterUI() {
        Intent intent = new Intent(RegisterMainUI.this, LaptopRegisterUI.class);
        startActivity(intent);
    }


}
