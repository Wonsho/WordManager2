package com.wons.wordmanager2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.add_word.value.WordList;

@Dao
public interface MyDao{
    @Insert
    void insertWord(Word word);
    @Insert
    void insertWordList(WordList wordList);

    @Delete
    void deleteWord(Word word);

    @Delete
    void deleteWordList(WordList wordList);

    @Update
    void upDateWord(Word word);
    @Update
    void upDateWordList(WordList wordList);

    @Query("SELECT * FROM wordlist")
    WordList[] getAllWordList();
    @Query("SELECT * FROM wordlist WHERE listId = :listId")
    WordList getWordListByCode(int listId);
    @Query("SELECT * FROM wordlist WHERE listName = :listName")
    WordList getWordListByListName(String listName);
    @Query("SELECT * FROM word WHERE listCode = :listCode")
    Word[] getWordsByListCode(int listCode);


}
