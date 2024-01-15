package com.elmernissi.myroomapplication.Entitys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//Stagiaire_table
@Entity(tableName = "stagiaires")
public class Stagiaire {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nom;
    public String email;
    public int age;
    public String niveau;
}

