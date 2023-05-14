package com.example.fraizrtcp.ui.user.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fraizrtcp.R;

public class ProfileActivity extends AppCompatActivity {
    private Button bt_edit;
    private TextView tname;
    private TextView tuser;
    private TextView tedad;
    private TextView ttelf;
    private TextView tfb;
    private TextView tins;
    private TextView ttwit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        hook();

    }

    private void hook() {
        tname = findViewById(R.id.editTextTextPersonName);
        tuser = findViewById(R.id.editTextTextPersonUsuario);
        tname = findViewById(R.id.editTextTextPersonEdad);
        ttelf = findViewById(R.id.editTextTextPersonTelf);
        tfb = findViewById(R.id.editTextTextPersonfb);
        tins = findViewById(R.id.editTextTextPersonIg);
        ttwit = findViewById(R.id.editTextTextPersonTwit);

    }
}