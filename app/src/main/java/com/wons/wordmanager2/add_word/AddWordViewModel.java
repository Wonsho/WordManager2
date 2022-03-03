package com.wons.wordmanager2.add_word;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.wons.wordmanager2.MainViewModel;
import com.wons.wordmanager2.MyDao;
import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.add_word.value.WordList;

import java.util.ArrayList;
import java.util.Arrays;

public class AddWordViewModel extends ViewModel {
   private MyDao myDao = MainViewModel.myDao;

   public void deleteWord(Word word) {
      myDao.deleteWord(word);
      WordList wordList = myDao.getWordListByCode(word.listCode);
      wordList.discount();
      myDao.upDateWordList(wordList);
   }

   public void insertWord(Word word) {
      myDao.insertWord(word);
      WordList wordList = myDao.getWordListByCode(word.listCode);
      wordList.addWordCount();
      myDao.upDateWordList(wordList);
      Log.e("insertWord","1");
   }

   public ArrayList<Word> getAllWords(String listName) {
     return searchWordByCode(myDao.getWordListByListName(listName).listId);
   }

   private ArrayList<Word> searchWordByCode(int listCode) {
      return new ArrayList<>(Arrays.asList(myDao.getWordsByListCode(listCode)));
   }

   public int getListCode(String listName) {
      return myDao.getWordListByListName(listName).listId;
   }
}
