package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordList {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int listId;
    public String listName;
    public String wordCountInList;

    public WordList(String listName, String wordCountInList) {
        this.listName = listName;
        this.wordCountInList = wordCountInList;
    }
}
