package com.wons.wordmanager2.add_word;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.wons.wordmanager2.MainViewModel;
import com.wons.wordmanager2.MyDao;
import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.add_word.value.WordList;
import com.wons.wordmanager2.add_word.value.WordPercentageOfCorrect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AddWord_ViewModel extends ViewModel {
   private MyDao myDao = MainViewModel.myDao;

   public void deleteWord(Word word) {
      deleteWordPercentage(word);
      myDao.deleteWord(word);
      WordList wordList = myDao.getWordListByCode(word.listCode);
      wordList.discount();
      myDao.upDateWordList(wordList);
   }

   public void insertWord(Word word) {
      insertWordPercentage(word);
      myDao.insertWord(word);
      WordList wordList = myDao.getWordListByCode(word.listCode);
      wordList.addWordCount();
      myDao.upDateWordList(wordList);
      Log.e("insertWord","1");
   }

   public ArrayList<Word> getAllWordsInList(String listName) {
     return searchWordByCode(myDao.getWordListByListName(listName).listId);
   }

   private ArrayList<Word> searchWordByCode(int listCode) {
      return new ArrayList<>(Arrays.asList(myDao.getWordsByListCode(listCode)));
   }

   public int getListCode(String listName) {
      return myDao.getWordListByListName(listName).listId;
   }

   public ArrayList<Word> getAllWord_inDB(String wordEnglish) {
      return new ArrayList<Word>(Arrays.asList(myDao.getSameWord(wordEnglish)));
   }

   private void insertWordPercentage(Word word) {
      WordPercentageOfCorrect percentageOfCorrect = new WordPercentageOfCorrect(word.english);
      if(myDao.getPercentageOfWord(word.english) == null) {
         myDao.insertWordPercentage(percentageOfCorrect);
      } else {
         return;
      }
   }

   private void deleteWordPercentage(Word word) {
      if(myDao.getSameWord(word.english).length == 1) {
         myDao.deleteWordPercentage(myDao.getPercentageOfWord(word.english));
      } else {
         return;
      }
   }

   public HashMap<String, String> getWordPercentageMap(ArrayList<Word>words) {
      HashMap<String, String> map = new HashMap<>();
      for(Word word : words) {
         map.put(word.english,String.valueOf(myDao.getPercentageOfWord(word.english).getPercentage_of_correct()).trim());
      }
      return map;
   }
}
