package com.example.fraizrtcp.ui.user.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fraizrtcp.R;
import com.example.fraizrtcp.ui.hall.HallActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SigInFragment extends Fragment {
    Button bt_register;
    EditText mail, pass;
    FirebaseAuth mAuth;
    FirebaseFirestore mfirestore;

    public SigInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sig_in, container, false);
        bt_register = v.findViewById(R.id.buttonloginsignIn);
        mail = v.findViewById(R.id.signineditTextTextEmailAddress);
        pass = v.findViewById(R.id.signineditTextNumberPassword);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = mail.getText().toString().trim();
                String passUser = pass.getText().toString().trim();
                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresa los campos", Toast.LENGTH_SHORT).show();
                } else {
                    sigin(emailUser, passUser);
                }
            }
        });
        return v;
    }

    private void sigin(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("email", emailUser);
                map.put("password", passUser);

                mfirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), HallActivity.class));
                        Toast.makeText(getContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}