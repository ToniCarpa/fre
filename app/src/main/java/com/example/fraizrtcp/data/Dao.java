package com.example.fraizrtcp.data;

import androidx.annotation.NonNull;

import com.example.fraizrtcp.utils.Providers;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.example.fraizrtcp.model.User;


import java.util.HashMap;

public class Dao {
    private static Dao dao;
    private FirebaseFirestore db;

    public Dao() {
        this.db = FirebaseFirestore.getInstance();
    }

    public static Dao getInstance() {
        if (dao == null) dao = new Dao();
        return dao;
    }

    // METHOD SAVE WITH GOOGLE SIGN IN
    public void saveLoginGoogle(GoogleSignInAccount signInAccount) {
        HashMap<String, String> collection = new HashMap<String, String>();

        if (signInAccount.getDisplayName() != null) {
            collection.put("name", signInAccount.getDisplayName());
        } else {
            collection.put("name", null);
        }

        if (signInAccount.getGivenName() != null) {
            collection.put("usuario", signInAccount.getGivenName());
        } else {
            collection.put("usuario", null);
        }

        collection.put("provider", Providers.GOOGLE.toString());

        db.collection("usuarios").document(signInAccount.getEmail())
                .set(collection, SetOptions.merge());
    }

    // METHODS SAVE USER WITH EMAIL & PASSWORD
    public void saveLogin(User user) {
        HashMap<String, String> collection = new HashMap<String, String>();

        if (user.getName() != null) {
            collection.put("name", user.getName());
        } else {
            collection.put("name", null);
        }

        if (user.getUsuario() != null) {
            collection.put("usuario", user.getUsuario());
        } else {
            collection.put("usuario", null);
        }

        collection.put("provider", user.getProvider().toString());

        db.collection("users").document(user.getEmail())
                .set(collection, SetOptions.merge());
    }

    // METHOD TO GET USER
    public void getUser(String email) {
        db.collection("users").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            User user = new User(email, Providers.valueOf(documentSnapshot.get("provider").toString()));

                            if (documentSnapshot.get("name") != null) {
                                if (!documentSnapshot.get("name").toString().equals("")) {
                                    user.setName(documentSnapshot.get("name").toString());
                                }
                            }

                            if (documentSnapshot.get("username") != null) {
                                if (!documentSnapshot.get("username").toString().equals("")) {
                                    user.setUsuario(documentSnapshot.get("usuario").toString());
                                }
                            }

                            //Controller.getInstance().returnCollectedData(user);
                        }
                    }
                });
    }

    //DELETE
    public void delete(String collectionPath, String email) {
        db.collection(collectionPath).document(email).delete();
    }
}
