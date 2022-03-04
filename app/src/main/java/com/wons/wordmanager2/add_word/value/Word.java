package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wordId;
    public String english;
    public String korean;
    public int listCode;

    public Word() {}

    public Word(String english, String korean, int listCode) {
        this.english = english;
        this.korean = korean;
        this.listCode = listCode;
    }
}
