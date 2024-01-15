package com.elmernissi.myroomapplication.repo;

import android.content.Context;

import com.elmernissi.myroomapplication.Dao.NoteDao;
import com.elmernissi.myroomapplication.Dao.StagiaireDao;
import com.elmernissi.myroomapplication.Entitys.Note;
import com.elmernissi.myroomapplication.Entitys.Stagiaire;
import com.elmernissi.myroomapplication.database.AppDatabase;

import android.os.AsyncTask;

import java.util.List;

import androidx.room.Room;


public class AppRepository {

    private StagiaireDao stagiaireDao;
    private NoteDao noteDao;

    public AppRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        stagiaireDao = database.stagiaireDao();
        noteDao = database.noteDao();
    }

    // Méthodes pour les opérations CRUD sur Stagiaire
    public void insertStagiaire(Stagiaire stagiaire) {
        new InsertStagiaireAsyncTask(stagiaireDao).execute(stagiaire);
    }

    public List<Stagiaire> getAllStagiaires() {
        return stagiaireDao.getAllStagiaires();
    }

    public Stagiaire getStagiaireById(int id) {
        return stagiaireDao.getStagiaireById(id);
    }

    public void updateStagiaire(Stagiaire stagiaire) {
        new UpdateStagiaireAsyncTask(stagiaireDao).execute(stagiaire);
    }

    public void deleteStagiaire(Stagiaire stagiaire) {
        new DeleteStagiaireAsyncTask(stagiaireDao).execute(stagiaire);
    }

    // Méthodes pour les opérations CRUD sur Note
    public void insertNote(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public List<Note> getNotesForStagiaire(int stagiaireId) {
        return noteDao.getNotesForStagiaire(stagiaireId);
    }

    public List<Note> getNotesHigherThan(float noteThreshold) {
        return noteDao.getNotesHigherThan(noteThreshold);
    }

    public List<Note> getNotesLowerThan(float noteThreshold) {
        return noteDao.getNotesLowerThan(noteThreshold);
    }

    public void updateValiderForHighNotes(float noteThreshold) {
        noteDao.updateValiderForHighNotes(noteThreshold);
    }

    public void deleteStagiairesWithLowNotes() {
        noteDao.deleteStagiairesWithLowNotes();
    }

    // AsyncTask pour exécuter les opérations de base de données de manière asynchrone
    private static class InsertStagiaireAsyncTask extends AsyncTask<Stagiaire, Void, Void> {
        private StagiaireDao stagiaireDao;

        private InsertStagiaireAsyncTask(StagiaireDao stagiaireDao) {
            this.stagiaireDao = stagiaireDao;
        }

        @Override
        protected Void doInBackground(Stagiaire... stagiaires) {
            stagiaireDao.insert(stagiaires[0]);
            return null;
        }
    }

    // Ajoutez d'autres AsyncTask pour les opérations CRUD sur Stagiaire et Note si nécessaire

    // Singleton pour garantir qu'une seule instance de la base de données Room est créée
    private static class AppDatabaseHolder {
        private static AppDatabase instance;

        static synchronized AppDatabase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                                context.getApplicationContext(),
                                AppDatabase.class,
                                "app_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }
    }

    private static class UpdateStagiaireAsyncTask extends AsyncTask<Stagiaire, Void, Void> {
        private StagiaireDao stagiaireDao;

        private UpdateStagiaireAsyncTask(StagiaireDao stagiaireDao) {
            this.stagiaireDao = stagiaireDao;
        }

        @Override
        protected Void doInBackground(Stagiaire... stagiaires) {
            stagiaireDao.update(stagiaires[0]);
            return null;
        }
    }

    private static class DeleteStagiaireAsyncTask extends AsyncTask<Stagiaire, Void, Void> {
        private StagiaireDao stagiaireDao;

        private DeleteStagiaireAsyncTask(StagiaireDao stagiaireDao) {
            this.stagiaireDao = stagiaireDao;
        }

        @Override
        protected Void doInBackground(Stagiaire... stagiaires) {
            stagiaireDao.delete(stagiaires[0]);
            return null;
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
}
