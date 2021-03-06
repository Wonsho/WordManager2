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

    // TODO: 2022-03-06 중복되는 단어장 리스트 보여주고 데이터 덮어씌우거나 뜻 합칠꺼나고 물음 , 취소하면 리스트에 추가 X 
    public void insertWord(WordEnglish wordEnglish, String wordKorean) {
        insertWordInfo(wordEnglish, wordKorean);
        if(myDao.getSameWord(wordEnglish.english).length != 0) {
            ArrayList<WordEnglish> wordEnglishes = new ArrayList<>(Arrays.asList(myDao.getSameWord(wordEnglish.english)));
            boolean check = wordEnglishes.get(0).checkHasExplain;
            Log.e("insert", "passed");
            wordEnglish.checkHasExplain = check;
        }
        myDao.insertWord(wordEnglish);
        WordList wordList = myDao.getWordListByCode(wordEnglish.listCode);
        wordList.addWordCount();
        myDao.upDateWordList(wordList);
    }

    public ArrayList<String> checkSameWordImDB(String wordEnglish) {  //todo 리턴값 리스트 이름들
        ArrayList<String> strArr = new ArrayList<>();
        ArrayList<WordEnglish> arr = new ArrayList<>(Arrays.asList(myDao.getSameWord(wordEnglish)));
        for(WordEnglish wordEnglish1 : arr) {
            strArr.add(myDao.getWordListByCode(wordEnglish1.listCode).listName);
        }
        return strArr;
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
        }
    }

    private void deleteWordInfo(WordEnglish wordEnglish) {
        if (myDao.getSameWord(wordEnglish.english).length == 1) {
            if (myDao.getWordExplain(wordEnglish.english) != null) {
                myDao.deleteWordExplain(myDao.getWordExplain(wordEnglish.english));
            } myDao.deleteWordPercentage(myDao.getPercentageOfWord(wordEnglish.english));

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

    public WordList getWordListByName(String listName) {
        return myDao.getWordListByListName(listName);
    }

    public boolean isCheckSameWordInList(int listCode, String english) {
        ArrayList<WordEnglish> arr = new ArrayList<>(Arrays.asList(myDao.getWordInList(listCode)));
        for (WordEnglish word : arr) {
            if (word.english.trim().equals(english.trim())) {
                return true;
            }
        }
        return false;
    }

}
