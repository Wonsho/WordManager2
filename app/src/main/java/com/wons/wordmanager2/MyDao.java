package com.wons.wordmanager2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.wordmanager2.add_word.value.WordEnglish;
import com.wons.wordmanager2.add_word.value.WordExplain;
import com.wons.wordmanager2.add_word.value.WordInfoByEnglish;
import com.wons.wordmanager2.add_word.value.WordList;
import com.wons.wordmanager2.setting.Setting;

@Dao
public interface MyDao{
    @Insert
    void insertWord(WordEnglish wordEnglish);
    @Insert
    void insertWordList(WordList wordList);
    @Insert
    void insertWordPercentage(WordInfoByEnglish percentageOfCorrect);
    @Insert
    void insertSetting(Setting setting);
    @Insert
    void insertWordExplain(WordExplain wordExplain);

    @Delete
    void deleteWord(WordEnglish wordEnglish);
    @Delete
    void deleteWordList(WordList wordList);
    @Delete
    void deleteWordPercentage(WordInfoByEnglish percentageOfCorrect);
    @Delete
    void deleteWordExplain(WordExplain wordExplain);

    @Update
    void updateWordInfoByEnglish(WordInfoByEnglish percentageOfCorrect);
    @Update
    void upDateWord(WordEnglish wordEnglish);
    @Update
    void upDateWordList(WordList wordList);
    @Update
    void upDateSetting(Setting setting);
    @Update
    void upDateWordExplain(WordExplain wordExplain);

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
    @Query("SELECT * FROM wordexplain WHERE word_english = :word_english")
    WordExplain getWordExplain(String word_english);
    @Query("SELECT * FROM setting WHERE settingCode = :settingCode")
    Setting getSetting(int settingCode);

}
