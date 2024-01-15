package com.elmernissi.myroomapplication.Entitys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes",
        foreignKeys = @ForeignKey(entity = Stagiaire.class,
        parentColumns = "id",
        childColumns = "stagiaireId",
        onDelete = ForeignKey.CASCADE))
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int idNote;
    public float note;
    public boolean valider;
    public int stagiaireId;
}
