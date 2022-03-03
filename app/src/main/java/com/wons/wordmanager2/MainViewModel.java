package com.wons.wordmanager2;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

public class MainViewModel extends ViewModel {
    public MainDataBase mainDataBase;
    public static MyDao myDao;

    public void dataBaseBuild(Context context) {
        mainDataBase = Room.databaseBuilder(context, MainDataBase.class, "MainDataBase").allowMainThreadQueries().build();
        myDao = mainDataBase.getDao();
    }
}
