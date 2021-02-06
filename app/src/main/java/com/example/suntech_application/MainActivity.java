package com.example.suntech_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Spinner typeSpinner;
    public static String userTypeValue;



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



        Button btnRegNow = (Button)findViewById(R.id.btnRegister);


        btnRegNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterMainUI();
            }
        });


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
