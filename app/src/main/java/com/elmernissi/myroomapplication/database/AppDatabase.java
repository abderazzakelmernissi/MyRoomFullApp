package com.elmernissi.myroomapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.elmernissi.myroomapplication.Dao.NoteDao;
import com.elmernissi.myroomapplication.Dao.StagiaireDao;
import com.elmernissi.myroomapplication.Entitys.Note;
import com.elmernissi.myroomapplication.Entitys.Stagiaire;

@Database(entities = {Stagiaire.class, Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StagiaireDao stagiaireDao();
    public abstract NoteDao noteDao();
    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
