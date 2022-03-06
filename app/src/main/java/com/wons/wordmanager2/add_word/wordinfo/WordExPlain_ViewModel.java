package com.wons.wordmanager2.add_word.wordinfo;

import androidx.lifecycle.ViewModel;

import com.wons.wordmanager2.MainViewModel;
import com.wons.wordmanager2.MyDao;
import com.wons.wordmanager2.add_word.value.WordEnglish;
import com.wons.wordmanager2.add_word.value.WordExplain;
import com.wons.wordmanager2.setting.Setting;
import com.wons.wordmanager2.setting.SettingCode;
import com.wons.wordmanager2.setting.SettingConstantValue;

public class WordExPlain_ViewModel extends ViewModel {
    MyDao dao = MainViewModel.myDao;

    public boolean checkDialogSetting() {
        Setting settingValue = dao.getSetting(SettingCode.SHOW_DIALOG);
        if(settingValue.settingValue == SettingConstantValue.DIALOG_SHOW) return true;
        return false;
    }

    public void changeDialogCode() {
        dao.upDateSetting(new Setting(SettingCode.SHOW_DIALOG, SettingConstantValue.DIALOG_NON_SHOW));
    }

    public String getWordExplain(String english) {
        if(dao.getWordExplain(english) != null) {
            return dao.getWordExplain(english).word_explain;
        } else {
            return null;
        }
    }

    public void saveExplain(String english, String str) {
        if(dao.getWordExplain(english) == null) {
            dao.insertWordExplain(new WordExplain(english, str));
        } else {
            dao.upDateWordExplain(new WordExplain(english, str));
        }
        WordEnglish[] wordEnglishes = dao.getSameWord(english);
        for(WordEnglish wordEnglish : wordEnglishes) {
            wordEnglish.checkHasExplain = true;
            dao.upDateWord(wordEnglish);
        }
    }

    public void deleteExplain(String english)  {
        WordEnglish[] wordEnglishes = dao.getSameWord(english);
        for(WordEnglish wordEnglish : wordEnglishes) {
            wordEnglish.checkHasExplain = false;
            dao.upDateWord(wordEnglish);
        }
    }
}
