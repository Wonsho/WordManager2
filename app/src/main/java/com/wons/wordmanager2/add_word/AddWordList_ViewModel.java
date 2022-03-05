package com.wons.wordmanager2.add_word;

import androidx.lifecycle.ViewModel;

import com.wons.wordmanager2.MainViewModel;
import com.wons.wordmanager2.MyDao;
import com.wons.wordmanager2.add_word.value.WordEnglish;
import com.wons.wordmanager2.add_word.value.WordList;

import java.util.ArrayList;
import java.util.Arrays;

public class AddWordList_ViewModel extends ViewModel {
    private MyDao myDao = MainViewModel.myDao;

    public ArrayList<WordList> getAllList() {
        return new ArrayList<>(Arrays.asList(myDao.getAllWordList()));
    }

    public void deleteWordList(WordList wordList) {
        ArrayList<WordEnglish> wordEnglishes = new ArrayList<>(Arrays.asList(myDao.getWordsByListCode(wordList.listId)));
        for (WordEnglish wordEnglish : wordEnglishes) {
            deleteWordPercentage(wordEnglish);
            myDao.deleteWord(wordEnglish);
        }
        myDao.deleteWordList(wordList);
    }

    public void insertWordList(WordList list) {
        myDao.insertWordList(list);
    }

    public boolean checkSameListName(WordList list) {
        if (myDao.getWordListByListName(list.listName) == null) {
            return true;
        }
        return false;
    }

    private void deleteWordPercentage(WordEnglish wordEnglish) {
        if (myDao.getSameWord(wordEnglish.english).length == 1) {
            myDao.deleteWordPercentage(myDao.getPercentageOfWord(wordEnglish.english));
            if (myDao.getWordExplain(wordEnglish.english) != null) {
                myDao.deleteWordExplain(myDao.getWordExplain(wordEnglish.english));
            }
        }
    }

    public String getWordInList(String listName) {
        WordList list = myDao.getWordListByListName(listName);
        ArrayList<WordEnglish> wordEnglishes = new ArrayList<>(Arrays.asList(myDao.getWordInList(list.listId)));
        StringBuilder str = new StringBuilder(listName + " 단어장에 담긴 단어입니다.\n\n");
        for (int i = 0; i < wordEnglishes.size(); i++) {
            if (i == 10) {
                str.append(wordEnglishes.get(i).english).append(" 등등");
                break;
            }
            if( i == 9) {
                str.append((i + 1) + ". " + wordEnglishes.get(i).english);
            } else {
                str.append((i + 1) + ". " + wordEnglishes.get(i).english).append("\n");

            }
        }
        return str.toString();
    }
}


