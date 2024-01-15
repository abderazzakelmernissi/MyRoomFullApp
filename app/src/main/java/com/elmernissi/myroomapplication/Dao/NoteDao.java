package com.elmernissi.myroomapplication.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.elmernissi.myroomapplication.Entitys.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes WHERE stagiaireId = :stagiaireId")
    List<Note> getNotesForStagiaire(int stagiaireId);

    @Query("SELECT * FROM notes WHERE note > :noteThreshold")
    List<Note> getNotesHigherThan(float noteThreshold);

    @Query("SELECT * FROM notes WHERE note < :noteThreshold")
    List<Note> getNotesLowerThan(float noteThreshold);

    @Query("UPDATE notes SET valider = 'Oui' WHERE note > :noteThreshold")
    void updateValiderForHighNotes(float noteThreshold);

    @Query("DELETE FROM stagiaires WHERE id IN (SELECT s.id FROM stagiaires s INNER JOIN notes n ON s.id = n.stagiaireId WHERE n.note < 5)")
    void deleteStagiairesWithLowNotes();

}
