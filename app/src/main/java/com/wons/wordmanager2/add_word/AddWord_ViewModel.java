package com.wons.wordmanager2.add_word;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.wons.wordmanager2.MainViewModel;
import com.wons.wordmanager2.MyDao;
import com.wons.wordmanager2.add_word.value.WordEnglish;
import com.wons.wordmanager2.add_word.value.WordInfoByEnglish;
import com.wons.wordmanager2.add_word.value.WordList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AddWord_ViewModel extends ViewModel {
    private MyDao myDao = MainViewModel.myDao;

    public void deleteWord(WordEnglish wordEnglish) {
        deleteWordInfo(wordEnglish);
        myDao.deleteWord(wordEnglish);
        WordList wordList = myDao.getWordListByCode(wordEnglish.listCode);
        wordList.discount();
        myDao.upDateWordList(wordList);
    }

    public void insertWord(WordEnglish wordEnglish, String wordKorean) {
        insertWordInfo(wordEnglish, wordKorean);
        myDao.insertWord(wordEnglish);
        WordList wordList = myDao.getWordListByCode(wordEnglish.listCode);
        wordList.addWordCount();
        myDao.upDateWordList(wordList);
        Log.e("insertWord", "1");
    }

    public ArrayList<WordEnglish> getAllWordsInList(String listName) {
        return searchWordByCode(myDao.getWordListByListName(listName).listId);
    }

    private ArrayList<WordEnglish> searchWordByCode(int listCode) {
        return new ArrayList<>(Arrays.asList(myDao.getWordsByListCode(listCode)));
    }

    public int getListCode(String listName) {
        return myDao.getWordListByListName(listName).listId;
    }

    public ArrayList<WordEnglish> getAllWord_inDB(String wordEnglish) {
        return new ArrayList<WordEnglish>(Arrays.asList(myDao.getSameWord(wordEnglish)));
    }

    private void insertWordInfo(WordEnglish wordEnglish, String wordKorean) {
        WordInfoByEnglish wordInfoByEnglish = new WordInfoByEnglish(wordEnglish.english, wordKorean);
        if (myDao.getPercentageOfWord(wordEnglish.english) == null) {
            myDao.insertWordPercentage(wordInfoByEnglish);
        } else {
            if(!myDao.getPercentageOfWord(wordEnglish.english).word_korean.equals(wordKorean)) {
                //todo 여기서 덮어 씌울껀지 제어
                myDao.updateWordInfoByEnglish(wordInfoByEnglish);
            }
        }
    }

    private void deleteWordInfo(WordEnglish wordEnglish) {
        if (myDao.getSameWord(wordEnglish.english).length == 1) {
            myDao.deleteWordPercentage(myDao.getPercentageOfWord(wordEnglish.english));
        } else {
            return;
        }
    }

    public HashMap<String, String> getWordPercentageMap(ArrayList<WordEnglish> wordEnglishes) {
        HashMap<String, String> map = new HashMap<>();
        for (WordEnglish wordEnglish : wordEnglishes) {
            map.put(wordEnglish.english, String.valueOf(myDao.getPercentageOfWord(wordEnglish.english).getPercentage_of_correct()).trim());
        }
        return map;
    }

    public HashMap<String, String> getWordKorean(ArrayList<WordEnglish> wordEnglishes) {
        HashMap<String, String> map = new HashMap<>();
        for (WordEnglish wordEnglish : wordEnglishes) {
            map.put(wordEnglish.english, String.valueOf(myDao.getPercentageOfWord(wordEnglish.english).word_korean).trim());
        }
        return map;
    }

    public boolean isCheckSameWordInList(int listCode, String english) {
       ArrayList<WordEnglish> arr = new ArrayList<>(Arrays.asList(myDao.getWordInList(listCode)));
       for(WordEnglish word : arr) {
           if(word.english.trim().equals(english.trim())) {
               return true;
           }
       }
       return false;
    }

}
