package com.wons.wordmanager2;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.add_word.value.WordList;
import com.wons.wordmanager2.add_word.value.WordPercentageOfCorrect;

@Database(entities =  {Word.class, WordList.class, WordPercentageOfCorrect.class}, version = 1)
abstract class MainDataBase extends RoomDatabase {
   abstract MyDao getDao();
}
