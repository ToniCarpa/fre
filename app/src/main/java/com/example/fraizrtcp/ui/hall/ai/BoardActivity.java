package com.example.fraizrtcp.ui.hall.ai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.example.fraizrtcp.R;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        fragmentManager = getSupportFragmentManager();
        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataOnBoard());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.BoardFrame, paperOnboardingFragment);
        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataOnBoard() {
        PaperOnboardingPage uno = new PaperOnboardingPage("Inventa con IA", "Selecciona los ingredientes, escoje unos parametros, dale al play y a ver que se cuece", Color.parseColor("#FF6C22"),R.drawable.board,R.drawable.cap );
        PaperOnboardingPage dos = new PaperOnboardingPage("Es Magia", "Genera todo tipo de recetas, para isnpirarte, motivarte y disfrutar", Color.parseColor("#FF5722"),R.drawable.board1,R.drawable.magia);
        PaperOnboardingPage tres = new PaperOnboardingPage("Prueba", "Cuanto más la uses, más acertarás, deja que la tecnologia entre en tu cocina también", Color.parseColor("#F44336"),R.drawable.board2,R.drawable.cocina );

        ArrayList<PaperOnboardingPage> elementos = new ArrayList<>();
        elementos.add(uno);
        elementos.add(dos);
        elementos.add(tres);

        return elementos;
    }
}