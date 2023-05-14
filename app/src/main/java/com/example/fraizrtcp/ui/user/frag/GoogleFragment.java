package com.example.fraizrtcp.ui.user.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fraizrtcp.R;
import com.example.fraizrtcp.ui.hall.HallActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class GoogleFragment extends Fragment {

    private static final int SIGN_IN_CODE = 777;
    private static final String TAG = "GOOGLE_SIGN_IN_TAG";

    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthList;

    private SignInButton bt_sigin;

    public GoogleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google, container, false);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);

        bt_sigin = view.findViewById(R.id.GoogleButon);

        bt_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Click to GooOOoogle SignIn");
                Intent i = googleSignInClient.getSignInIntent();
                startActivityForResult(i, SIGN_IN_CODE);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthList = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            }
        };

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE) {
            Log.d(TAG, "Google SignIn intent resutl");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogleAccount(account);
            } catch (Exception e) {
                Log.d(TAG, "onActivityResutl" + e.getMessage());
            }
        }
    }

    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // LOGIN OK
                        Log.d(TAG, "onsucces:Logged In");

                        //USER
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        String mail = firebaseUser.getEmail();

                        Log.d(TAG, "onSucces: Email: " + mail);
                        Log.d(TAG, "onSucces: UID: " + uid);
                        if (authResult.getAdditionalUserInfo().isNewUser()) {
                            // NEW USER
                            Log.d(TAG, "onSucces: cuenta creada " + mail);
                            Toast.makeText(getContext(), "Cuenta creada" + mail, Toast.LENGTH_SHORT);

                        } else {
                            // USER IN
                            Log.d(TAG, "onSucces: usuario ya exsite " + mail);
                            Toast.makeText(getContext(), "Usuario ya existe" + mail, Toast.LENGTH_SHORT);
                        }
                        startActivity(new Intent(getContext(), HallActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // LOGIN FAIL
                        Log.d(TAG, "onfail: Login failed" + e.getMessage());
                    }
                });
    }
}