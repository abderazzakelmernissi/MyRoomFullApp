package com.elmernissi.myroomapplication.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.elmernissi.myroomapplication.Entitys.Stagiaire;

import java.util.List;

@Dao
public interface StagiaireDao {
    @Insert
    void insert(Stagiaire stagiaire);

    @Update
    void update(Stagiaire stagiaire);

    @Delete
    void delete(Stagiaire stagiaire);

    @Query("SELECT * FROM stagiaires WHERE id = :id")
    Stagiaire getStagiaireById(int id);

    @Query("SELECT * FROM stagiaires WHERE id IN (:ids)")
    List<Stagiaire> loadAllByIds(int[] ids);

    @Query("SELECT * FROM stagiaires WHERE age > :ageThreshold")
    List<Stagiaire> getStagiairesOlderThan(int ageThreshold);

    @Query("SELECT * FROM stagiaires")
    List<Stagiaire> getAllStagiaires();


}
