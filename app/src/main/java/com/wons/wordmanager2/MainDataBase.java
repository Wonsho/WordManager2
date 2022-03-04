package com.wons.wordmanager2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.wordmanager2.add_word.value.WordEnglish;
import com.wons.wordmanager2.add_word.value.WordInfoByEnglish;
import com.wons.wordmanager2.add_word.value.WordList;

@Database(entities =  {WordEnglish.class, WordList.class, WordInfoByEnglish.class}, version = 1)
abstract class MainDataBase extends RoomDatabase {
   abstract MyDao getDao();
}
