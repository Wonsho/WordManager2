package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class WordEnglish {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wordId;
    public String english;
    public boolean checkHasExplain;
    public int listCode;

    public WordEnglish(String english, int listCode) {
        this.english = english;
        this.listCode = listCode;
        checkHasExplain = false;
    }
}
