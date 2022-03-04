package com.wons.wordmanager2.add_word;

import androidx.lifecycle.ViewModel;

import com.wons.wordmanager2.MainViewModel;
import com.wons.wordmanager2.MyDao;
import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.add_word.value.WordList;

import java.util.ArrayList;
import java.util.Arrays;

public class AddWordList_ViewModel extends ViewModel {
   private MyDao myDao = MainViewModel.myDao;

   public ArrayList<WordList> getAllList() {
       return new ArrayList<>(Arrays.asList(myDao.getAllWordList()));
   }
   public void deleteWordList(WordList wordList) {
       ArrayList<Word> words = new ArrayList<>(Arrays.asList(myDao.getWordsByListCode(wordList.listId)));
       for(Word word : words) {
           deleteWordPercentage(word);
           myDao.deleteWord(word);
       }
       myDao.deleteWordList(wordList);
   }
   public void insertWordList(WordList list) {
       myDao.insertWordList(list);
   }

   public boolean checkSameListName(WordList list) {
       if(myDao.getWordListByListName(list.listName)==null) {
           return true;
       }
       return false;
   }

    private void deleteWordPercentage(Word word) {
        if(myDao.getSameWord(word.english).length == 1) {
            myDao.deleteWordPercentage(myDao.getPercentageOfWord(word.english));
        } else {
            return;
        }
    }
}
