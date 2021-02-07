package com.example.suntech_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMainUI extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_main_ui);


        Button btnLapList = (Button)findViewById(R.id.btnAdminViewLaptops);

        btnLapList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLaptopListUI();
            }
        });




    }

    private void openLaptopListUI() {
        Intent intent = new Intent(AdminMainUI.this,LaptopListUI.class);
        startActivity(intent);
    }
}
