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

    public WordList(String listName) {
        this.listName = listName;
        this.wordCountInList = "0";
    }

    public String getWordCountInList() {
        return wordCountInList;
    }

    public void addWordCount() {
        int count = Integer.parseInt(wordCountInList);
        this.wordCountInList = String.valueOf(count++);
    }
    public void discount() {
        int count = Integer.parseInt(wordCountInList);
        if(count <= 0) {
            wordCountInList = "0";
            return;
        }
        this.wordCountInList = String.valueOf(count--);
    }
}
