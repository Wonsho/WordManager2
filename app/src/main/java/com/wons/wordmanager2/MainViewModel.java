package com.wons.wordmanager2;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.wons.wordmanager2.setting.Setting;
import com.wons.wordmanager2.setting.SettingCode;
import com.wons.wordmanager2.setting.SettingConstantValue;

public class MainViewModel extends ViewModel {
    public MainDataBase mainDataBase;
    public static MyDao myDao;

    public void dataBaseBuild(Context context) {
        mainDataBase = Room.databaseBuilder(context, MainDataBase.class, "MainDataBase").allowMainThreadQueries().build();
        myDao = mainDataBase.getDao();
        if(myDao.getSetting(SettingCode.SHOW_DIALOG) == null) {
            myDao.insertSetting(new Setting(SettingCode.SHOW_DIALOG, SettingConstantValue.DIALOG_SHOW));
        }
        if(myDao.getSetting(SettingCode.TTS_SPEED) == null)  {
            myDao.insertSetting(new Setting(SettingCode.TTS_SPEED, -1));
        }
    }
}
