package com.wons.wordmanager2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.wordmanager2.add_word.value.WordEnglish;
import com.wons.wordmanager2.add_word.value.WordInfoByEnglish;
import com.wons.wordmanager2.add_word.value.WordList;

@Dao
public interface MyDao{
    @Insert
    void insertWord(WordEnglish wordEnglish);
    @Insert
    void insertWordList(WordList wordList);
    @Insert
    void insertWordPercentage(WordInfoByEnglish percentageOfCorrect);

    @Delete
    void deleteWord(WordEnglish wordEnglish);

    @Delete
    void deleteWordList(WordList wordList);
    @Delete
    void deleteWordPercentage(WordInfoByEnglish percentageOfCorrect);

    @Update
    void updateWordInfoByEnglish(WordInfoByEnglish percentageOfCorrect);
    @Update
    void upDateWord(WordEnglish wordEnglish);
    @Update
    void upDateWordList(WordList wordList);

    @Query("SELECT * FROM wordlist")
    WordList[] getAllWordList();
    @Query("SELECT * FROM wordlist WHERE listId = :listId")
    WordList getWordListByCode(int listId);
    @Query("SELECT * FROM wordlist WHERE listName = :listName")
    WordList getWordListByListName(String listName);
    @Query("SELECT * FROM WordEnglish WHERE listCode = :listCode")
    WordEnglish[] getWordsByListCode(int listCode);
    @Query("SELECT * FROM WordEnglish WHERE english = :english")
    WordEnglish[] getSameWord(String english);
    @Query("SELECT * FROM WordInfoByEnglish WHERE word_english = :word_english")
    WordInfoByEnglish getPercentageOfWord(String word_english);
    @Query("SELECT * FROM (SELECT * FROM WordEnglish) WHERE listCode = :listCode")
    WordEnglish[] getWordInList(int listCode);

}
