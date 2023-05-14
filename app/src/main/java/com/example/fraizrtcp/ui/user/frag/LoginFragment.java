package com.example.fraizrtcp.ui.user.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fraizrtcp.R;
import com.example.fraizrtcp.ui.answers.ErrorFragment;
import com.example.fraizrtcp.ui.hall.HallActivity;
import com.example.fraizrtcp.ui.user.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private Button bt_sigin;
    private EditText mail;
    private EditText pass;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mail = view.findViewById(R.id.logineditTextTextEmailAddress);
        pass = view.findViewById(R.id.logineditTextNumberPassword);
        bt_sigin = view.findViewById(R.id.buttonloginsignIn);
        mAuth = FirebaseAuth.getInstance();

        bt_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = mail.getText().toString().trim();
                String passUser = pass.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresa los campos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailUser, passUser);
                }
            }
        });
        return view;
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

              if (task.isSuccessful()) {
                startActivity(new Intent(getContext(), HallActivity.class));
                Toast.makeText(getContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                //Frag OK
            } else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Frag NO
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}