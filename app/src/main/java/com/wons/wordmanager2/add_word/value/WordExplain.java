package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordExplain {

    @PrimaryKey
    @NonNull
    public String word_english;
    public String word_explain;

    public WordExplain(String word_english, String word_explain) {
        this.word_english = word_english;
        this.word_explain = word_explain;
    }
}
