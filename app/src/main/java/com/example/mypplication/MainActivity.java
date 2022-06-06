package com.example.mypplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button1);
        con=findViewById(R.id.connection_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });
        
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ConnectionDB.connect();
                    Toast.makeText(MainActivity.this, "connected", Toast.LENGTH_SHORT).show();
                    ConnectionDB.disconnect();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "not found", Toast.LENGTH_SHORT).show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    Toast.makeText(MainActivity.this, "inreach", Toast.LENGTH_SHORT).show();
                }
                
                
            }
        });
    }
}