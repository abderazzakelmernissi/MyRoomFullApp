package com.elmernissi.myroomapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.elmernissi.myroomapplication.Entitys.Note;
import com.elmernissi.myroomapplication.Entitys.Stagiaire;
import com.elmernissi.myroomapplication.R;
import com.elmernissi.myroomapplication.repo.AppRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppRepository repository;
    private List<Stagiaire> stagiaires;
    private
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new AppRepository(getApplicationContext());
        stagiaires = new ArrayList<>();

        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        Button btnHighNotes = findViewById(R.id.btnHighNotes);
        Button btnOlderThan18 = findViewById(R.id.btnOlderThan18);
        Button btnLowNotesEmail = findViewById(R.id.btnLowNotesEmail);
        Button btnDeleteLowNotes = findViewById(R.id.btnDeleteLowNotes);
        Button btnUpdateValider = findViewById(R.id.btnUpdateValider);

        btnHighNotes.setOnClickListener(v -> displayStagiairesWithHighNotes());
        btnOlderThan18.setOnClickListener(v -> displayStagiairesOlderThan18());
        btnLowNotesEmail.setOnClickListener(v -> displayEmailsOfStagiairesWithLowNotes());
        btnDeleteLowNotes.setOnClickListener(v -> deleteStagiairesWithLowNotes());
        btnUpdateValider.setOnClickListener(v -> updateValiderForHighNotes());
    }

    private void displayStagiairesWithHighNotes() {
        // Affiche les noms des stagiaires qui ont une note supérieure à 10
        List<Note> highNotes = repository.getNotesHigherThan(10.0f);
        stagiaires.clear();
        for (Note note : highNotes) {
            Stagiaire stagiaire = repository.getStagiaireById(note.stagiaireId);
            stagiaires.add(stagiaire);
        }
        updateListView();
    }

    private void displayStagiairesOlderThan18() {
        // Affiche les noms des stagiaires qui ont plus de 18 ans
        /*stagiaires.clear();
        stagiaires.addAll(repository.getStagiairesOlderThan(18));
        updateListView();*/
    }

    private void displayEmailsOfStagiairesWithLowNotes() {
        // Affiche les emails des stagiaires qui ont une note inférieure à 10
        List<Note> lowNotes = repository.getNotesLowerThan(10.0f);
        List<String> emails = new ArrayList<>();
        for (Note note : lowNotes) {
            Stagiaire stagiaire = repository.getStagiaireById(note.stagiaireId);
            emails.add(stagiaire.email);
        }
        displayResult(emails);
    }

    private void deleteStagiairesWithLowNotes() {
        // Supprime les stagiaires qui ont une note inférieure à 5
        repository.deleteStagiairesWithLowNotes();
        refreshData();
    }

    private void updateValiderForHighNotes() {
        // Met à jour la valeur 'Oui' dans le cas 'Valider' si le stagiaire obtient une note supérieure à 10
        repository.updateValiderForHighNotes(10.0f);
        refreshData();
    }

    private void refreshData() {
        stagiaires.clear();
        stagiaires.addAll(repository.getAllStagiaires());
        updateListView();
    }

    private void updateListView() {
        adapter.clear();
        for (Stagiaire stagiaire : stagiaires) {
            adapter.add(stagiaire.nom);
        }
        adapter.notifyDataSetChanged();
    }

    private void displayResult(List<String> result) {
        // Affiche le résultat dans votre interface utilisateur (par exemple, Toast, AlertDialog, etc.)
    }
}
